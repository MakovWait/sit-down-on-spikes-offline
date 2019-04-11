package by.mkwt.games.hungry.content;


import by.mkwt.games.hungry.logic.engine.Constants;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.ShakeComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.TextureComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.BackgroundComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.TrapComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.JumpComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.RigidBodyComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.state.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;

public class EntityBuilder {

    public static void addPlayer(PooledEngine engine) {
        Entity entity = engine.createEntity();
        TextureComponent tex = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        VelocityComponent vel = engine.createComponent(VelocityComponent.class);
        TransformComponent trans = engine.createComponent(TransformComponent.class);
        ShakeComponent shake = engine.createComponent(ShakeComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        JumpComponent jump = engine.createComponent(JumpComponent.class);
        RigidBodyComponent rigid = engine.createComponent(RigidBodyComponent.class);

        Texture t = new Texture(Gdx.files.internal("tiles/hero8.png"));

        tex.texture = new TextureRegion(t);

        trans.polygon = new Polygon(new float[]{
                0, 0,
                tex.texture.getRegionWidth(), 0,
                tex.texture.getRegionWidth(), tex.texture.getRegionHeight(),
                0, tex.texture.getRegionHeight()
        });

        float bias = 7f;

        rigid.polygon = new Polygon(new float[]{
                bias, 0,
                tex.texture.getRegionWidth() - bias, 0,
                tex.texture.getRegionWidth() - 2 * bias, tex.texture.getRegionHeight(),
                2 * bias, tex.texture.getRegionHeight()
        });

        trans.polygon.setPosition(0, Constants.rows.get(0));
        vel.velocity.set(6, 0f, 0);
        shake.shakeForce = -5f;

        engine.addEntity(entity
                .add(tex)
                .add(shake)
                .add(player)
                .add(vel)
                .add(trans)
                .add(state)
                .add(jump)
                .add(rigid));
    }

    public static void addMap(PooledEngine engine) {
        TextureRegion rg = new TextureRegion(new Texture(Gdx.files.internal("tiles/ground1.png")));

        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 14; k++) {
                TransformComponent trans = engine.createComponent(TransformComponent.class);
                TextureComponent tex = engine.createComponent(TextureComponent.class);

                tex.texture = rg;

                trans.polygon = new Polygon(new float[]{
                        0, 0,
                        tex.texture.getRegionWidth(), 0,
                        tex.texture.getRegionWidth(), tex.texture.getRegionHeight(),
                        0, tex.texture.getRegionHeight()
                });

                trans.polygon.setPosition(k * Constants.CELL.x, Constants.rows.get(i) - Constants.CELL.y);
                trans.z = -1;

                if (i == 1) {
                    trans.polygon.setRotation(180f);
                    Vector3 tmp = new Vector3(0, Constants.CELL.y, 0);
                    trans.polygon.translate(tmp.x, tmp.y);
                }


                engine.addEntity(new Entity().add(trans).add(tex));
            }
        }
    }

    public static void addSpikes(PooledEngine engine) {
        TextureRegion rg1 = new TextureRegion(new Texture(Gdx.files.internal("tiles/spike.png")));

        //TODO Заполнение сверху вниз!
        for (int i = 1; i >= 0; i--) {
            for (int k = 0; k < 14; k++) {
                if (k > 4 && k < 9) {
                    TransformComponent trans = engine.createComponent(TransformComponent.class);
                    TextureComponent tex = engine.createComponent(TextureComponent.class);
                    TrapComponent trap = engine.createComponent(TrapComponent.class);
                    RigidBodyComponent rigid = engine.createComponent(RigidBodyComponent.class);

                    tex.texture = rg1;

                    trans.polygon = new Polygon(new float[]{
                            0, 0,
                            tex.texture.getRegionWidth(), 0,
                            tex.texture.getRegionWidth(), tex.texture.getRegionHeight(),
                            0, tex.texture.getRegionHeight()
                    });

                    rigid.polygon = new Polygon(trans.polygon.getVertices());

                    trans.polygon.setPosition(k * Constants.CELL.x, Constants.rows.get(i));
                    trans.z = 1;
                    trans.isHidden = true;

                    if (i == 1) {
                        trans.polygon.setRotation(180f);
                        Vector3 tmp = new Vector3(0, -Constants.CELL.y, 0);
                        trans.polygon.translate(tmp.x, tmp.y);
                    }

                    engine.addEntity(new Entity().add(trans).add(tex).add(trap).add(rigid));
                }
            }
        }
    }

    public static void addBackGround(PooledEngine engine) {
        TransformComponent trans = engine.createComponent(TransformComponent.class);
        TextureComponent tex = engine.createComponent(TextureComponent.class);
        BackgroundComponent background = engine.createComponent(BackgroundComponent.class);

        TextureRegion rg = new TextureRegion(new Texture(Gdx.files.internal("tiles/background.png")));

        tex.texture = rg;

        trans.polygon = new Polygon(new float[]{
                0, 0,
                tex.texture.getRegionWidth(), 0,
                tex.texture.getRegionWidth(), tex.texture.getRegionHeight(),
                0, tex.texture.getRegionHeight()
        });

        trans.polygon.setPosition(Constants.WORLD_WIDTH, Constants.rows.get(0) + 50);
        trans.z = -2;

        trans.polygon.setScale(2.0f, 2.0f);


        engine.addEntity(new Entity().add(trans).add(tex).add(background));
    }

}
