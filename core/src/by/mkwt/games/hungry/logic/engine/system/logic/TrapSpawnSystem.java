package by.mkwt.games.hungry.logic.engine.system.logic;

import by.mkwt.games.hungry.content.TrapPatternsHolder;
import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.TrapComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;


public class TrapSpawnSystem extends IteratingSystem {

    private Array<Entity> traps;

    public TrapSpawnSystem() {
        super(Family.all(TransformComponent.class, TrapComponent.class).get());

        traps = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (int i = 0; i < traps.size; i++) {
            TransformComponent trans = CMHolder.transform.get(traps.get(i));
            if (Integer.valueOf(GameEngine.getInstance().getCurrentScore()) > 0) {
                trans.isHidden = TrapPatternsHolder.getPattern().get(i);
            } else {
                trans.isHidden = true;
            }
        }



        traps.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        traps.add(entity);
    }
}
