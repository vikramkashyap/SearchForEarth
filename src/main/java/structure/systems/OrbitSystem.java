package structure.systems;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import structure.components.OrbitComponent;
import structure.components.PositionComponent;

public class OrbitSystem extends EntitySystem{
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<OrbitComponent> om = ComponentMapper.getFor(OrbitComponent.class);
	
	public OrbitSystem() {}
	
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, OrbitComponent.class).get());
	}
	
	public void update(float deltaTime) {
		ArrayList<Entity> all = new ArrayList<Entity>();
		ArrayList<Entity> todo;
		
		for (Entity e : entities) {
			all.add(e);
		}
		
		todo = (ArrayList<Entity>) all.clone();
		
		int i = 0;
		while (!todo.isEmpty()) {
			System.out.println(todo.size() + " " + i);
			
			Entity e = todo.get(i);
			
			PositionComponent pc = pm.get(e);
			OrbitComponent oc = om.get(e);
			
			if (pm.has(oc.center) && (!all.contains(oc.center) || !todo.contains(oc.center))) {
				System.out.println(oc.radius);
				
				float centerx = pm.get(oc.center).x;
				float centery = pm.get(oc.center).y;
				
				oc.heading = (float) ((oc.heading + oc.speed*deltaTime/1000) % (Math.PI*2));
				
				//pc.x = (float) (centerx + Math.cos(oc.heading) * oc.radius);
				//pc.y = (float) (centery + Math.sin(oc.heading) * oc.radius);
				
				todo.remove(i);
			}
			else
				if (++i == entities.size()) i = 0;
		}
	}
}
