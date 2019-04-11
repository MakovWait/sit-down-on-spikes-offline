package by.mkwt.games.hungry.ui.table;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuTable {

    private Table table;
    private Label highScoreValue;
    private Label attempt;

    private Vector3 color = new Vector3(0.5f, 0.71f, 0.63f);

    public MenuTable() {
    }

    public void buildTable(Skin skin) {
        table = new Table();
        table.setFillParent(true);

        Label label = new Label("high score:", skin);
        highScoreValue = new Label("", skin);
        Label start = new Label("tap to start", skin);
        Label totalAttempt = new Label("attempt", skin);
        attempt = new Label("1", skin);

        totalAttempt.setColor(color.x, color.y, color.z, 1);
        attempt.setColor(color.x, color.y, color.z, 1);
        label.setColor(color.x, color.y, color.z, 1);
        highScoreValue.setColor(color.x, color.y, color.z, 1);
        start.setColor(color.x, color.y, color.z, 1);

        table.add(label).top().expandX();
        table.row();
        table.add(highScoreValue).expandY().top();
        table.row();

        table.add(totalAttempt).center();
        table.row();
        table.add(attempt).top();
        table.row();
        table.add(start).padBottom(15);
        table.bottom();
    }

    public Table getTable() {
        return table;
    }

    public void setHighScore(String val) {
        table.getCell(highScoreValue).getActor().setText(val);
    }

    public void setAttempt(String val) {
        table.getCell(attempt).getActor().setText(val);
    }
}
