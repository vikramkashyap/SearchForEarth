package structure.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import structure.components.PositionComponent;
import structure.components.RenderComponent;

import static helper.Artist.*;
import static helper.TextureTools.*;

public class RenderSystem extends EntitySystem{
private ImmutableArray<Entity> entities;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
	
	public RenderSystem() {}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(RenderComponent.class).get());
	}
	
	public void update(float deltaTime) {
		
		for (Entity e : entities) {
			PositionComponent pc = pm.get(e);
			RenderComponent rc = rm.get(e);
			
			drawQuad(pc.x, pc.y, rc.width, rc.height, 0, rc.texture);
			//drawSlice(pc.x, pc.y, rc.width/2, 0f, (float) Math.PI*2f, rc.texture);
		}
	}
}
