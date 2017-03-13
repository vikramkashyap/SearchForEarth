package structure.components;

import org.newdawn.slick.opengl.Texture;

import com.badlogic.ashley.core.Component;

public class RenderComponent implements Component{
	public float width = 0;
	public float height = 0;
	public Texture texture;
}
