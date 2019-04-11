package by.mkwt.games.hungry.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.*;

public class TrapPatternsHolder {

    private static ArrayMap<Integer, Array<Boolean>> patterns;

    private static int currentPatternNum = 1;

    public static void load() {
        patterns = new ArrayMap<Integer, Array<Boolean>>();

        FileHandle file = Gdx.files.internal("data/data.txt");
        String text = file.readString();

        String[] lines = text.split("\n");
        int i = 0;
        for (String line : lines) {
            String[] values = line.split(",");
            Array<Boolean> pattern = new Array<Boolean>();
            
            for (String value : values) {
                if (value.contains("#")) {
                    pattern.add(false);
                } else {
                    pattern.add(true);
                }
            }

            patterns.put(i, pattern);

            i++;
        }


    }

    public static void reset() {
        currentPatternNum = 0;
    }

    public static void getNewCurrentPatternNum() {
        currentPatternNum = MathUtils.random(0, patterns.size - 1);
    }

    public static Array<Boolean> getPattern() {
        return patterns.get(currentPatternNum);
    }
}
