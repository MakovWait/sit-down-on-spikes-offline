package by.mkwt.games.hungry.logic.engine.entity;

import by.mkwt.games.hungry.logic.engine.entity.component.graphic.AnimationComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.ShakeComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.graphic.TextureComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.BackgroundComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.PlayerComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.marker.TrapComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.JumpComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.RigidBodyComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.TransformComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.physic.VelocityComponent;
import by.mkwt.games.hungry.logic.engine.entity.component.state.StateComponent;
import com.badlogic.ashley.core.ComponentMapper;

public class CMHolder {

    public static ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
    public static ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static ComponentMapper<ShakeComponent> shake = ComponentMapper.getFor(ShakeComponent.class);
    public static ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static ComponentMapper<JumpComponent> jump  = ComponentMapper.getFor(JumpComponent.class);
    public static ComponentMapper<TrapComponent> trap = ComponentMapper.getFor(TrapComponent.class);
    public static ComponentMapper<RigidBodyComponent> rigid = ComponentMapper.getFor(RigidBodyComponent.class);
    public static ComponentMapper<BackgroundComponent>  background = ComponentMapper.getFor(BackgroundComponent.class);

    //public static ComponentMapper<>  = ComponentMapper.getFor();

}
