package helper;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Artist {
	final static int circle_sections = 100;
	final static float angle_change = (float) (2*Math.PI/circle_sections);
	
	public static void drawLine(float x, float y, float x2, float y2) {
		glBegin(GL_LINES);
		glVertex2f(x, y);
		glVertex2f(x2, y2);
		glEnd();
	}
	

	public static void drawQuad(float x, float y, float width, float height, float heading, Texture t) {
		if (t != null) {
			t.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0,1);
			glVertex2f(x-width/2, 480- (y - height/2));	//Bottom left
			glTexCoord2f(1,1);
			glVertex2f(x+width/2, 480- (y- height/2));	//Bottom right
			glTexCoord2f(1,0);
			glVertex2f(x + width/2, 480- (y + height/2));	//Top right
			glTexCoord2f(0,0);
			glVertex2f(x - width/2, 480- (y + height/2));	//Top left
			glEnd();
			
			/*
			Color.white.bind();
			glBegin(GL_QUADS);
			glVertex2f(x-width/2, 480- (y - height/2));	//Bottom left
			glVertex2f(x+width/2, 480- (y- height/2));	//Bottom right
			glVertex2f(x + width/2, 480- (y + height/2));	//Top right
			glVertex2f(x - width/2, 480- (y + height/2));	//Top left
			glEnd();*/
		}
		else {
			glBegin(GL_QUADS);
			glVertex2f(x-width/2, 480- (y - height/2));	//Bottom left
			glVertex2f(x+width/2, 480- (y- height/2));	//Bottom right
			glVertex2f(x + width/2, 480- (y + height/2));	//Top right
			glVertex2f(x - width/2, 480- (y + height/2));	//Top left
			glEnd();
		}
	}
	
	public static void drawSlice(float x, float y, float radius, float start, float end, Texture t) {
		t.bind();
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(0.5f, 0.5f);
		glVertex2f(x, y);
		for (float angle = start; angle < end+angle_change; angle+=angle_change) {
			glTexCoord2f((float)(Math.cos(angle)*.5+.5), (float)(-Math.sin(angle)*.5+.5));
			System.out.println((float)(Math.cos(angle)*.5+.5) + " " + (float)(-Math.sin(angle)*.5+.5));
			glVertex2f((float)Math.cos(angle)*radius+x, (float)-Math.sin(angle)*radius+y);
		}
		glEnd();
	}
}
