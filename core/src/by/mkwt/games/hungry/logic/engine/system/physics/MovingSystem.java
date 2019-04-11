package by.mkwt.games.hungry.logic.engine.system.physics;

import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class MovingSystem extends IteratingSystem {

    public MovingSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = CMHolder.transform.get(entity);
        VelocityComponent vel = CMHolder.velocity.get(entity);

        trans.polygon.translate(vel.velocity.x, vel.velocity.y);
    }
}
