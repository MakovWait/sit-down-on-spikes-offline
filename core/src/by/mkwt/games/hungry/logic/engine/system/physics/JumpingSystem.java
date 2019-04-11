package by.mkwt.games.hungry.logic.engine.system.physics;

import by.mkwt.games.hungry.logic.engine.Constants;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.JumpComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.state.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;

public class JumpingSystem extends IteratingSystem {

    private Vector3 toNegativeJumpForce = new Vector3(-1, 1, 1);

    public JumpingSystem() {
        super(Family.all(
                PlayerComponent.class,
                TransformComponent.class,
                VelocityComponent.class,
                StateComponent.class,
                JumpComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = CMHolder.transform.get(entity);
        VelocityComponent vel = CMHolder.velocity.get(entity);
        StateComponent state = CMHolder.state.get(entity);
        JumpComponent jump = CMHolder.jump.get(entity);


       /* if (Gdx.input.isTouched()) {
            if (state.currentState != StateComponent.State.inFalling) {

                state.currentState = StateComponent.State.inJump;

                if (trans.position.y < pos.rows.get(pos.currentRow) + jump.jumpLimit) {

                    if (vel.velocity.x > 0) {

                        trans.position.add(jump.jumpForce.cpy());

                    } else {

                        trans.position.add(jump.jumpForce.cpy().scl(new Vector3(-1, 1, 1)).cpy());

                    }

                } else {
                    state.currentState = StateComponent.State.inFalling;
                }

            }
        }*/

        if (state.currentState == StateComponent.State.inJump) {

            if (trans.polygon.getY() < Constants.rows.get(0) + jump.jumpLimit) {

                if (vel.velocity.x > 0) {

                    trans.polygon.translate(jump.jumpForce.x, jump.jumpForce.y);

                } else {

                    Vector3 resultJumpForce = jump.jumpForce.cpy().scl(toNegativeJumpForce);
                    trans.polygon.translate(resultJumpForce.x, resultJumpForce.y);

                }

            } else {
                state.currentState = StateComponent.State.inFalling;
            }

        }

        if (trans.polygon.getY() > Constants.rows.get(0)) {

            trans.polygon.translate(jump.gravity.x, jump.gravity.y);

            if (state.currentState != StateComponent.State.inJump) {

                state.currentState = StateComponent.State.inFalling;

            }

        } else {

            state.currentState = StateComponent.State.onGround;

        }

    }
}
