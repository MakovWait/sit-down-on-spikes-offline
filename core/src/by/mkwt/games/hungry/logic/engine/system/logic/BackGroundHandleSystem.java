package by.mkwt.games.hungry.logic.engine.system.logic;

import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.BackgroundComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class BackGroundHandleSystem extends IteratingSystem {
    public BackGroundHandleSystem() {
        super(Family.all(BackgroundComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CMHolder.transform.get(entity).polygon.setRotation(MathUtils.cos(GameEngine.time));
    }
}
