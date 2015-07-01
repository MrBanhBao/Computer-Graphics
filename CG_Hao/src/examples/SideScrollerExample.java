package examples;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class SideScrollerExample {
	public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(640,480));
            Display.setTitle("SideScroller");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
         
        // init OpenGL here
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
        float translate_x = 0;
         
        while (!Display.isCloseRequested()) {
            // render OpenGL here
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
        	glPushMatrix();
        	
        	//Shift screen to the right
        	glTranslatef(translate_x, 0, 0);
        	
        	if (Mouse.isButtonDown(0) && Mouse.getX() > 0 && Mouse.getX() < 639) {
            translate_x += Mouse.getDX();
        	}
        	
            
        	
        	glBegin(GL_QUADS);
        		glVertex2i(400, 400); //Upper Left Corner
        		glVertex2i(450, 400); //Upper Right Corner
        		glVertex2i(450, 450); //Bottom Right Corner
        		glVertex2i(400, 450); //Bottom Left
        	glEnd();
        	
        	
            glBegin(GL_LINES);
            	glVertex2i(100, 100);
            	glVertex2i(200, 200);
            glEnd();
            
            glPopMatrix();
        	
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
     
    public static void main(String[] argv) {
    	SideScrollerExample displayWindow = new SideScrollerExample();
        displayWindow.start();
    }
}
