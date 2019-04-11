package by.mkwt.games.hungry.logic.engine;

import by.mkwt.games.hungry.content.EntityBuilder;
import by.mkwt.games.hungry.content.PreferencesLoader;
import by.mkwt.games.hungry.content.ScoreHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.TextureComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import by.mkwt.games.hungry.logic.engine.system.control.PlayerControlSystem;
import by.mkwt.games.hungry.logic.engine.system.logic.BackGroundHandleSystem;
import by.mkwt.games.hungry.logic.engine.system.logic.GameLogicSystem;
import by.mkwt.games.hungry.logic.engine.system.logic.ScoreProcessSystem;
import by.mkwt.games.hungry.logic.engine.system.logic.TrapSpawnSystem;
import by.mkwt.games.hungry.logic.engine.system.physics.CollisionDetectingSystem;
import by.mkwt.games.hungry.logic.engine.system.physics.JumpingSystem;
import by.mkwt.games.hungry.logic.engine.system.physics.MovingSystem;
import by.mkwt.games.hungry.logic.engine.system.rendering.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static by.mkwt.games.hungry.logic.engine.Constants.WORLD_HEIGHT;
import static by.mkwt.games.hungry.logic.engine.Constants.WORLD_WIDTH;

public class GameEngine implements Disposable {

    public enum GameState {
        run,
        pause,
        demonstrate
    }

    public static GameEngine instance;
    public static float time;
    public static int attempt;

    private PooledEngine engine;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ExtendViewport viewport;

    private GameState currentState;

    private ScoreHolder scoreHolder;

    private GameEngine() {
        scoreHolder = new ScoreHolder();
        attempt = PreferencesLoader.getInstance().getAttempt();

        loadCamera();
        loadSpriteBatch();
        loadEngine();

        setCurrentState(GameState.demonstrate);
    }

    private void loadCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    }

    private void loadSpriteBatch() {
        batch = new SpriteBatch();
    }

    public static void loadGameEngine() {
        instance = new GameEngine();
    }

    private void loadEngine() {
        engine = new PooledEngine();

        engine.addSystem(new FlippingSystem());
        engine.addSystem(new ShakeSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem(camera, batch));
        engine.addSystem(new MovingSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new JumpingSystem());
        engine.addSystem(new CollisionDetectingSystem());
        engine.addSystem(new ScoreProcessSystem(scoreHolder));
        engine.addSystem(new GameLogicSystem());
        engine.addSystem(new TrapSpawnSystem());
        engine.addSystem(new BackGroundHandleSystem());

        EntityBuilder.addPlayer(engine);
        EntityBuilder.addSpikes(engine);
        EntityBuilder.addMap(engine);
        EntityBuilder.addBackGround(engine);
    }

    public void update(float delta) {
        time += delta;

        engine.update(delta);
    }

    public void reset() {
        scoreHolder.resetCurrentScore();
        Entity player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();

        player.getComponent(PlayerComponent.class).health = 1;
        player.getComponent(PlayerComponent.class).score = 0;
        player.getComponent(TransformComponent.class).polygon.setPosition(0, Constants.rows.get(0));
        player.getComponent(VelocityComponent.class).velocity.set(6, 0, 0);
        if (player.getComponent(TextureComponent.class).texture.isFlipX()) {
            player.getComponent(TransformComponent.class).isNeedToFlip = true;
        }
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            //TODO англ моя беда...
            throw new IllegalStateException("GameEngine.loadEngine should be called first");
        }
        return instance;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public ExtendViewport getViewport() {
        return viewport;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        switch (currentState) {
            case run:
                for (EntitySystem es : engine.getSystems()) {
                    if (es.getClass() != RenderingSystem.class) {
                        es.setProcessing(true);
                    }
                }

                PreferencesLoader.getInstance().setAttempt(++attempt);

                reset();
                break;

            case demonstrate:
                scoreHolder.processResultScore();
                for (EntitySystem es : engine.getSystems()) {
                    if (es.getClass() != RenderingSystem.class) {
                        es.setProcessing(false);
                    }
                }
                break;

            case pause:

                break;
        }

        this.currentState = currentState;
    }

    public String getCurrentScore() {
        return String.valueOf(scoreHolder.getCurrentScore());
    }

    public String getHighScore() {
        return String.valueOf(scoreHolder.getHighScore());
    }

    public static String getAttempt() {
        return String.valueOf(attempt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
