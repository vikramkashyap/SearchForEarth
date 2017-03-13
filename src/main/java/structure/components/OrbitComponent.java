package structure.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class OrbitComponent implements Component{
	//Entity being orbited
	public Entity center;
	
	//Radius of circular orbit
	public float radius = 0.0f;
	
	//Current heading of planet from center
	public float heading = 0.0f;
	
	//Radians orbited per second
	public float speed = 0.0f;
	
}
