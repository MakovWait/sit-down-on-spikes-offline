package by.mkwt.games.hungry.logic.engine.entity.component.physic;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;

public class TransformComponent implements Component {

    public Polygon polygon;
    public float z = 0;

    public boolean isNeedToFlip = false;

    public boolean isHidden = false;

}
