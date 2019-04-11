package by.mkwt.games.hungry.logic.engine.system.control;

import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.JumpComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.state.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

public class PlayerControlSystem extends IteratingSystem {

    public PlayerControlSystem() {
        super(Family.all(
                PlayerComponent.class,
                StateComponent.class,
                JumpComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = CMHolder.state.get(entity);
        JumpComponent jump = CMHolder.jump.get(entity);

        if (Gdx.input.isTouched()) {
            touchProcess(state);
        } else {
            noneTouchProcess(state);
        }
    }

    private void touchProcess(StateComponent state) {
        if (state.currentState == StateComponent.State.onGround) {

            state.currentState = StateComponent.State.inJump;

        }
    }

    private void noneTouchProcess(StateComponent state) {
        if (state.currentState == StateComponent.State.inJump) {

            state.currentState = StateComponent.State.inFalling;

        }
    }
}
