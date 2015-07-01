package examples;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Simple3DExample {
	public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setTitle("Hello, 3D!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
         
        // init OpenGL here
     // init OpenGL here
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
     // Create a new perspective with 30 degree angle (field of view), 640 / 480 aspect ratio, 0.001f zNear, 100 zFar
        gluPerspective((float) 30, 640f/480f, 0.001f, 100);
        glMatrixMode(GL_MODELVIEW);
        
        Point[] points = new Point[10000];
        Random random = new Random();
        
        for(int i = 0; i<points.length; i++){
        	// Set the point at the array index to 
            // x = random between -50 and +50
            // y = random between -50 and +50
            // z = random between  0  and -200
            points[i] = new Point((random.nextFloat() - 0.5f) * 100f, (random.nextFloat() - 0.5f) * 100f,
                    random.nextInt(200) - 200);
            
        }
        
        float speed = 0.0f;
        
        while (!Display.isCloseRequested()) {
        	
            // render OpenGL here
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
        	glTranslatef(0,0,speed);
        	
        	
        	glBegin(GL_POINTS);
        		for(Point point : points){
        			glVertex3f(point.x, point.y, point.z);
        		}
        	glEnd();
            
        	if(Keyboard.isKeyDown(Keyboard.KEY_UP))
        		speed += 0.1f;
        	if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        		speed -= 0.1f;
        	if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        		speed = 0f;
        	if(Keyboard.isKeyDown(Keyboard.KEY_C)){
        		speed = 0f;
        		glLoadIdentity();
        	}
        	
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
	 
	private static class Point {

        final float x;
        final float y;
        final float z;

        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
     
    public static void main(String[] argv) {
    	Simple3DExample example3d = new Simple3DExample();
    	example3d.start();
    }
	
}
