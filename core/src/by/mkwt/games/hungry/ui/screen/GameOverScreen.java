package by.mkwt.games.hungry.ui.screen;

import by.mkwt.games.hungry.MyGame;
import by.mkwt.games.hungry.logic.engine.Constants;
import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.ui.table.PlayTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameOverScreen implements Screen {

    private float calledTime;
    private MyGame parent;

    private PlayTable playTable;
    private Stage stage;

    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (GameEngine.time - calledTime > 0.5f) {
                calledTime = 0;
                parent.changeScreen(MyGame.Screens.menu);
            }
            return true;
        }
    };

    public GameOverScreen(MyGame myGame) {
        parent = myGame;

        stage = parent.getStage();

        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        playTable = new PlayTable();
        playTable.buildTable(skin);
        playTable.getTable().row();
        Label l = new Label("....", skin);
        l.setColor(playTable.color.x, playTable.color.y, playTable.color.z, 1);

        playTable.getTable().add(l).padBottom(15);

        stage.addActor(playTable.getTable());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void render(float delta) {
        GameEngine.getInstance().update(delta);

        GameEngine.getInstance().update(delta);
        playTable.setCurrentScore(GameEngine.getInstance().getCurrentScore());

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

    }

    public void setCallTime(float calledTime) {
        this.calledTime = calledTime;
    }
}
