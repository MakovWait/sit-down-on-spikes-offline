package by.mkwt.games.hungry.logic.engine.system.rendering;

import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.TextureComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class FlippingSystem extends IteratingSystem {

    public FlippingSystem() {
        super(Family.all(
                TransformComponent.class,
                TextureComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = CMHolder.transform.get(entity);
        TextureComponent tex = CMHolder.texture.get(entity);

        if (trans.isNeedToFlip) {
            tex.texture.flip(true, false);
            trans.isNeedToFlip = false;
        }
    }
}
