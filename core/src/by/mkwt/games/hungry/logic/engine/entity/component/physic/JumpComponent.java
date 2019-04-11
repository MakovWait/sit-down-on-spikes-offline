package by.mkwt.games.hungry.logic.engine.entity.component.physic;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class JumpComponent implements Component {

    public Vector3 jumpForce = new Vector3(5f, 9f, 0f);

    public Vector3 gravity = new Vector3(0f, -5f, 0f);
    public float jumpLimit = 100;

}
