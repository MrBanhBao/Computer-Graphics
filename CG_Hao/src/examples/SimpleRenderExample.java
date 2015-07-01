package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class SimpleRenderExample {
	public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setTitle("Hello, World!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
         
        // init OpenGL here
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
         
        while (!Display.isCloseRequested()) {
            // render OpenGL here
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
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
        	
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
     
    public static void main(String[] argv) {
    	SimpleRenderExample displayWindow = new SimpleRenderExample();
        displayWindow.start();
    }
}
