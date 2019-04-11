package by.mkwt.games.hungry.logic.engine.entity.component.state;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {

    public enum State {
        onGround,
        inJump,
        inFalling
    }

    public State currentState = State.onGround;

}
