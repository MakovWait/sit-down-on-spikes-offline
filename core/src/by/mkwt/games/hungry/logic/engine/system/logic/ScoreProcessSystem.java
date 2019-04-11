package by.mkwt.games.hungry.logic.engine.system.logic;

import by.mkwt.games.hungry.content.ScoreHolder;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class ScoreProcessSystem extends IteratingSystem {

    private ScoreHolder scoreHolder;

    public ScoreProcessSystem(ScoreHolder scoreHolder) {
        super(Family.all(PlayerComponent.class).get());

        this.scoreHolder = scoreHolder;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        scoreHolder.setCurrentScore(CMHolder.player.get(entity).score);
    }
}
