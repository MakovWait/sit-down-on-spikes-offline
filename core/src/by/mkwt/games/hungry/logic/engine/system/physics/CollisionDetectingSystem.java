package by.mkwt.games.hungry.logic.engine.system.physics;

import by.mkwt.games.hungry.logic.engine.entity.CMHolder;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.RigidBodyComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class CollisionDetectingSystem extends IteratingSystem {

    private Array<Entity> queue;
    private Entity player;

    public CollisionDetectingSystem() {
        super(Family.all(RigidBodyComponent.class, TransformComponent.class).get());

        queue = new Array<Entity>();
    }

    //TODO костыль
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Polygon transPoly;
        Polygon rigidPoly;

        transPoly = player.getComponent(TransformComponent.class).polygon;
        rigidPoly = player.getComponent(RigidBodyComponent.class).polygon;

        rigidPoly.setPosition(transPoly.getX(), transPoly.getY());
        rigidPoly.setScale(transPoly.getScaleX(), transPoly.getScaleY());
        rigidPoly.setRotation(transPoly.getRotation());
        rigidPoly.setOrigin(transPoly.getOriginX(), transPoly.getOriginY());

        for (Entity entity : queue) {
            transPoly = entity.getComponent(TransformComponent.class).polygon;
            rigidPoly = entity.getComponent(RigidBodyComponent.class).polygon;

            rigidPoly.setPosition(transPoly.getX(), transPoly.getY());
            rigidPoly.setScale(transPoly.getScaleX(), transPoly.getScaleY());
            rigidPoly.setRotation(transPoly.getRotation());
            rigidPoly.setOrigin(transPoly.getOriginX(), transPoly.getOriginY());

            if (Intersector.overlapConvexPolygons(CMHolder.rigid.get(player).polygon, CMHolder.rigid.get(entity).polygon)) {
                CMHolder.player.get(player).health--;
            }
        }

        queue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (CMHolder.player.get(entity) != null) {
            player = entity;
            return;
        }

        if (CMHolder.transform.get(entity).isHidden) {
            return;
        }

        if (CMHolder.trap.get(entity) != null) {
            queue.add(entity);
        }

    }
}
