package structure;

import com.badlogic.ashley.core.Entity;

import structure.components.*;

public class EntityGenerator {

	public static Entity genPlanet() {
		Entity e = new Entity();
		
		e.add(new PositionComponent());
		e.add(new MassComponent());
		e.add(new OrbitComponent());
		e.add(new RenderComponent());
		
		return e;
	}
	
	public static Entity genStar() {
		Entity e = new Entity();
		
		e.add(new PositionComponent());
		e.add(new MassComponent());
		e.add(new RenderComponent());
		
		return e;
	}
	
}
