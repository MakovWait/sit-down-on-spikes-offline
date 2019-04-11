package by.mkwt.games.hungry.ui.screen;

import by.mkwt.games.hungry.MyGame;
import by.mkwt.games.hungry.logic.engine.Constants;
import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.ui.table.MenuTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class MenuScreen implements Screen {

    private MyGame parent;
    private MenuTable menuTable;
    private Stage stage;

    private float calledTime;

    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (GameEngine.time - calledTime > 0.1f) {
                parent.changeScreen(MyGame.Screens.game);
                calledTime = 0;
            }
            return true;
        }
    };

    public MenuScreen(MyGame game) {
        parent = game;
        stage = parent.getStage();

        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        menuTable = new MenuTable();
        menuTable.buildTable(skin);

        stage.addActor(menuTable.getTable());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void render(float delta) {
        GameEngine.getInstance().update(delta);
        menuTable.setHighScore(GameEngine.getInstance().getHighScore());
        menuTable.setAttempt(GameEngine.getAttempt());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        GameEngine.getInstance().getCamera().position.set(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT + 69, 0);
        GameEngine.getInstance().getCamera().zoom = 2f;
        GameEngine.getInstance().getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        //TODO skin
    }

    public void setCalledTime(float calledTime) {
        this.calledTime = calledTime;
    }
}
