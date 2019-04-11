package by.mkwt.games.hungry.logic.engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;

public class Constants {

    public static final float WORLD_WIDTH = 220;
    public static final float WORLD_HEIGHT = 200;

    public static final Vector2 CELL = new Vector2(32, 32);

    public static final float HALF_WORLD_WIDTH = WORLD_WIDTH / 2f;
    public static final float HALF_WORLD_HEIGHT = WORLD_HEIGHT / 2f;

    public static final float DOUBLE_WORLD_WIDTH = WORLD_WIDTH * 2f;
    public static final float DOUBLE_WORLD_HEIGHT = WORLD_HEIGHT * 2f;

    public static final ArrayMap<Integer, Float> rows = new ArrayMap<Integer, Float>();

    static  {
        rows.put(0, 200f);
        rows.put(1, 330f);
    }
}
