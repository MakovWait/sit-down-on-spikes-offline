package by.mkwt.games.hungry.logic.engine.system.rendering;

import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.ShakeComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.state.StateComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class ShakeSystem extends IteratingSystem {

    public ShakeSystem() {
        super(Family.all(TransformComponent.class, ShakeComponent.class, StateComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = CMHolder.transform.get(entity);
        ShakeComponent shake = CMHolder.shake.get(entity);
        StateComponent state = CMHolder.state.get(entity);
        VelocityComponent vel = CMHolder.velocity.get(entity);

        if (state.currentState.equals(StateComponent.State.onGround)) {
            trans.polygon.setRotation(shake.shakeForce * MathUtils.sin(GameEngine.time * 32));
        } else {
            float a = 2.3f;

            if (vel.velocity.x < 0) {
                a = -a;
            }

            trans.polygon.rotate( a * shake.shakeForce);
        }
    }
}
