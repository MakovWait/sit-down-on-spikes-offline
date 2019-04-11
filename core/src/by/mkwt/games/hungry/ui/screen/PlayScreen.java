package by.mkwt.games.hungry.ui.screen;

import by.mkwt.games.hungry.MyGame;
import by.mkwt.games.hungry.logic.engine.Constants;
import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.ui.table.PlayTable;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PlayScreen implements Screen {

    private MyGame parent;

    private PlayTable playTable;
    private Stage stage;

    private InputAdapter inputAdapter = new InputAdapter();

    public PlayScreen(MyGame parent) {
        this.parent = parent;

        stage = parent.getStage();

        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        playTable = new PlayTable();
        playTable.buildTable(skin);

        stage.addActor(playTable.getTable());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void render(float delta) {
        if (!GameEngine.getInstance().getCurrentState().equals(GameEngine.GameState.run)) {
            parent.changeScreen(MyGame.Screens.game_over);
            return;
        }

        GameEngine.getInstance().update(delta);
        playTable.setCurrentScore(GameEngine.getInstance().getCurrentScore());

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        GameEngine.getInstance().getCamera().position.set(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT + 69, 0);
        GameEngine.getInstance().getCamera().zoom = 2f;
        GameEngine.getInstance().getViewport().update(width, height);

        stage.getViewport().update(width, height, true);
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

    }
}
