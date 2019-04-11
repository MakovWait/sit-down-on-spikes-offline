package by.mkwt.games.hungry.logic.engine.system.rendering;

import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity entityA, Entity entityB) {

        float aZ = CMHolder.transform.get(entityA).z;
        float bZ = CMHolder.transform.get(entityB).z;

        if (aZ > bZ) {
            return 1;
        } else if (aZ < bZ) {
            return -1;
        }

        return 0;
    }

}
