package by.mkwt.games.hungry.content;

import android.animation.PropertyValuesHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesLoader {

    private static PreferencesLoader instance;

    private Preferences preferences;

    private PreferencesLoader() {
        preferences = Gdx.app.getPreferences("re");

        TrapPatternsHolder.load();

        load();
    }

    public static PreferencesLoader getInstance() {
        if (instance == null) {
            instance = new PreferencesLoader();
        }

        return instance;
    }

    private void load() {
        if (!preferences.contains("HighScore")) {
            preferences.putInteger("HighScore", 0);
        }
    }

    public int getHighScore() {
        return preferences.getInteger("HighScore");
    }

    public void setHighScore(int newHighScore) {
        preferences.putInteger("HighScore", newHighScore);
        preferences.flush();
    }

    public int getAttempt() {
        return preferences.getInteger("Attempt");
    }

    public void setAttempt(int newHighScore) {
        preferences.putInteger("Attempt", newHighScore);
        preferences.flush();
    }
}
