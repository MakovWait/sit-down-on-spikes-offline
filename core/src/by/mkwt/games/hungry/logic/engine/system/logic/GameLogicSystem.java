package by.mkwt.games.hungry.logic.engine.system.logic;

import by.mkwt.games.hungry.content.TrapPatternsHolder;
import by.mkwt.games.hungry.logic.engine.Constants;
import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class GameLogicSystem extends IteratingSystem {

    private TransformComponent trans;
    private VelocityComponent vel;
    private PlayerComponent player;

    public GameLogicSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class, PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        trans = CMHolder.transform.get(entity);
        vel = CMHolder.velocity.get(entity);
        player = CMHolder.player.get(entity);

        if (player.health <= 0) {
            GameEngine.getInstance().setCurrentState(GameEngine.GameState.demonstrate);
            return;
        }

        rotate();

        final int DELAY = 5;

        if (vel.velocity.x > 0 && trans.polygon.getX() < 15 + DELAY || vel.velocity.x < 0 && trans.polygon.getX() > Constants.DOUBLE_WORLD_WIDTH - 20 - DELAY) {
            if (vel.velocity.x > 0 && vel.velocity.x != 1) {
                vel.velocity.x = 1f;
            } else if (vel.velocity.x < 0 && vel.velocity.x != -1){
                vel.velocity.x = -1f;
            }
        } else {
            if (vel.velocity.x > 0) {
                vel.velocity.x = 6;
            } else if (vel.velocity.x < 0){
                vel.velocity.x = -6;
            }
        }

        if (trans.polygon.getX() < 0) {
            trans.polygon.setPosition(0, trans.polygon.getY());
            reverseDirection();
        } else if (trans.polygon.getX() > Constants.DOUBLE_WORLD_WIDTH) {
            trans.polygon.setPosition(Constants.DOUBLE_WORLD_WIDTH - 5, trans.polygon.getY());
            reverseDirection();
        }

        //ЗАБЕГ ЗА ПОЛЕ ВИДИМОСТИ
        /*if (trans.polygon.getX() < -80) {
            trans.polygon.setPosition(-80, trans.polygon.getY());
            reverseDirection();
        } else if (trans.polygon.getX() > Constants.DOUBLE_WORLD_WIDTH + 50) {
            trans.polygon.setPosition(Constants.DOUBLE_WORLD_WIDTH + 50, trans.polygon.getY());
            reverseDirection();
        }*/
    }

    private void reverseDirection() {
        vel.velocity.scl(-1);
        trans.isNeedToFlip = true;
        switchRotation();
        TrapPatternsHolder.getNewCurrentPatternNum();

        player.score++;
    }

    private void rotate() {
        //GameEngine.getInstance().getCamera().rotate(1f);
        //GameEngine.getInstance().getCamera().rotate(MathUtils.random(360));
    }

    private void switchRotation() {
       // GameEngine.getInstance().getCamera().rotate(MathUtils.random(180));
       //GameEngine.getInstance().getCamera().rotate(MathUtils.random(360));
        GameEngine.getInstance().getCamera().rotate(MathUtils.random(90));
    }
}
