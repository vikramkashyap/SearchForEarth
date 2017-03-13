import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static structure.EntityGenerator.*;
import static helper.TextureTools.*;
import static org.lwjgl.opengl.GL11.*;
import static helper.Artist.*;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import structure.components.OrbitComponent;
import structure.components.PositionComponent;
import structure.components.RenderComponent;
import structure.systems.OrbitSystem;
import structure.systems.RenderSystem;
 
/**
 * OGPC 2017 Game by Late Code
 */
public class Game {
	public static int width = 640;
	public static int height = 480;
	
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Search for Earth");
            Display.create();
            
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, 640, 480, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glEnable(GL_TEXTURE_2D);
            
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
        } catch (LWJGLException e) {
            System.err.println("Error initalizing Display");
            System.exit(1);
        }
        
        Engine engine = new Engine();
        long oldtime = System.currentTimeMillis();
        
        Entity planet = genPlanet();
        Entity sun = genStar();
        Entity moon = genPlanet();
        
        sun.getComponent(PositionComponent.class).x = 200;
        sun.getComponent(PositionComponent.class).y = 200;
        sun.getComponent(RenderComponent.class).height = 100;
        sun.getComponent(RenderComponent.class).width= 100;
        sun.getComponent(RenderComponent.class).texture = loadTexture("red");
        planet.getComponent(PositionComponent.class).x = 200;
        planet.getComponent(PositionComponent.class).y = 300;
        planet.getComponent(OrbitComponent.class).center = sun;
        planet.getComponent(OrbitComponent.class).radius = 100;
        planet.getComponent(OrbitComponent.class).speed = 0.5f;
        planet.getComponent(RenderComponent.class).height = 50;
        planet.getComponent(RenderComponent.class).width= 50;
        planet.getComponent(RenderComponent.class).texture = loadTexture("blue");
        moon.getComponent(PositionComponent.class).x = 200;
        moon.getComponent(PositionComponent.class).y = 335;
        moon.getComponent(OrbitComponent.class).center = planet;
        moon.getComponent(OrbitComponent.class).radius = 35;
        moon.getComponent(OrbitComponent.class).speed = 1f;
        moon.getComponent(RenderComponent.class).height = 20;
        moon.getComponent(RenderComponent.class).width= 20;
        moon.getComponent(RenderComponent.class).texture = loadTexture("white");
        
        engine.addEntity(sun);
        engine.addEntity(planet);
        engine.addEntity(moon);
        engine.addSystem(new OrbitSystem());
        engine.addSystem(new RenderSystem());
        
        while (!Display.isCloseRequested()) {
        	glClearColor(0.0f, 0.0f, 0.0f, 1.0f );
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
        	long currenttime = System.currentTimeMillis();
        	long deltatime = currenttime - oldtime;
        	oldtime = currenttime;
        	
        	engine.update(deltatime);
        	
            Display.update();
            Display.sync(60);
        }
 
        Display.destroy();
        System.exit(0);
    }
}