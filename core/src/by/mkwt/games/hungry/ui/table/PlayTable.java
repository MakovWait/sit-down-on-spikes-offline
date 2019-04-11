package by.mkwt.games.hungry.ui.table;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PlayTable {

    private Table table;
    private Label currentScoreValue;

    public Vector3 color = new Vector3(0.5f, 0.71f, 0.63f);

    public void buildTable(Skin skin) {
        table = new Table();
        table.setFillParent(true);

        currentScoreValue = new Label("", skin);
        currentScoreValue.setColor(color.x, color.y, color.z, 1f);

        table.add(currentScoreValue).expandY().top();
    }

    public Table getTable() {
        return table;
    }

    public void setCurrentScore(String val) {
        table.getCell(currentScoreValue).getActor().setText(val);
    }

}
