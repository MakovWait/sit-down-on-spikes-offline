package by.mkwt.games.hungry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class Test {
    public static void main(String[] args) {

        Array<Boolean> pattern2 = new Array<Boolean>();

        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 4) {
                pattern2.add(false);
            }
            pattern2.add(true);
        }



    }
}
