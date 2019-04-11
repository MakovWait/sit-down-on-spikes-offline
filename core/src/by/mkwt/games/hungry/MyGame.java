package by.mkwt.games.hungry;

import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.ui.screen.GameOverScreen;
import by.mkwt.games.hungry.ui.screen.MenuScreen;
import by.mkwt.games.hungry.ui.screen.PlayScreen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MyGame extends com.badlogic.gdx.Game {

    public enum Screens {
        menu,
        game,
        game_over
    }

    private GameOverScreen gameOverScreenScreen;
    private PlayScreen playScreen;
    private MenuScreen menuScreen;

    @Override
    public void create() {
        GameEngine.loadGameEngine();

        setScreen(new MenuScreen(this));
    }

    public void changeScreen(Screens currentScreen) {
        switch (currentScreen) {
            case menu:
                if (menuScreen == null) {
                    menuScreen = new MenuScreen(this);
                }

                GameEngine.getInstance().setCurrentState(GameEngine.GameState.demonstrate);
                menuScreen.setCalledTime(GameEngine.time);

                setScreen(menuScreen);
                break;

            case game:
                if (playScreen == null) {
                    playScreen = new PlayScreen(this);
                }
                GameEngine.getInstance().setCurrentState(GameEngine.GameState.run);

                setScreen(playScreen);
                break;

            case game_over:
                if (gameOverScreenScreen == null) {
                    gameOverScreenScreen = new GameOverScreen(this);
                }

                gameOverScreenScreen.setCallTime(GameEngine.time);
                setScreen(gameOverScreenScreen);
                break;
        }
    }

    public Stage getStage() {
        return new Stage(new ExtendViewport(
                GameEngine.getInstance().getViewport().getMinWorldWidth(),
                GameEngine.getInstance().getViewport().getMinWorldHeight()
        ));
    }

}
