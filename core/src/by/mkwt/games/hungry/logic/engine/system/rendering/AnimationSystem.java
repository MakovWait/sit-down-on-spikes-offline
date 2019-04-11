package by.mkwt.games.hungry.logic.engine.system.rendering;

import by.mkwt.games.hungry.logic.engine.GameEngine;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.AnimationComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.TextureComponent;
import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, TextureComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent ani = CMHolder.animation.get(entity);
        TextureComponent tex = CMHolder.texture.get(entity);

        tex.texture = ani.animation.getKeyFrame(GameEngine.time, true);
    }
}
