package by.mkwt.games.hungry.content;

public class ScoreHolder {

    private int highScore;
    private int currentScore;

    public ScoreHolder() {
        this.highScore = PreferencesLoader.getInstance().getHighScore();
    }

    public void processResultScore() {
        if (currentScore > highScore) {
            highScore = currentScore;
            PreferencesLoader.getInstance().setHighScore(highScore);
        }
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
