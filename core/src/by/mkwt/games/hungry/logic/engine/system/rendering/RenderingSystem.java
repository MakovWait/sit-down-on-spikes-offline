package by.mkwt.games.hungry.logic.engine.system.rendering;

import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.TextureComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class RenderingSystem extends SortedIteratingSystem {

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private OrthographicCamera camera;

    private ShapeRenderer shapeRenderer;

    private boolean debugMode = false;

    public RenderingSystem(OrthographicCamera camera, SpriteBatch batch) {
        super(Family.all(
                TransformComponent.class,
                TextureComponent.class).get(),
                new ZComparator()
        );

        this.camera = camera;
        this.batch = batch;

        renderQueue = new Array<Entity>();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        render();

        if (debugMode) {
            debugRender();
        }

        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    private void render() {
        Gdx.gl.glClearColor(0.16f, 0.16f, 0.16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent tex = CMHolder.texture.get(entity);
            TransformComponent trans = CMHolder.transform.get(entity);

            if (tex == null || trans == null || trans.isHidden) {
                continue;
            }

            float width = tex.texture.getRegionWidth();
            float height = tex.texture.getRegionHeight();

            trans.polygon.setOrigin(width / 2f, height / 2f);

            batch.draw(tex.texture,
                    trans.polygon.getX(), trans.polygon.getY(),
                    trans.polygon.getOriginX(), trans.polygon.getOriginY(),
                    width, height,
                    trans.polygon.getScaleX(), trans.polygon.getScaleY(),
                    trans.polygon.getRotation());
        }

        batch.end();
    }

    private void debugRender() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1f, 1f, 1f, 1);

        for (Entity entity : renderQueue) {
            TextureComponent tex = CMHolder.texture.get(entity);
            TransformComponent trans = CMHolder.transform.get(entity);

            if (tex == null || trans == null || trans.isHidden) {
                continue;
            }

            if (CMHolder.rigid.get(entity) != null) {
                shapeRenderer.polygon(CMHolder.rigid.get(entity).polygon.getTransformedVertices());
            } else {
                shapeRenderer.polygon(trans.polygon.getTransformedVertices());
            }

        }

        shapeRenderer.end();
    }

    public RenderingSystem setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
        return this;
    }
}
