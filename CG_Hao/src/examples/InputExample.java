package examples;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class InputExample {
	private List<Box> shapes = new ArrayList<Box>(16);
	private boolean isSelected = false;
	
	public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setTitle("Input Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        
        shapes.add(new Box(15, 15));
        shapes.add(new Box(100, 150));
         
        // init OpenGL here
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
         
        while (!Display.isCloseRequested()) {
            // render OpenGL here
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
        	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        		Display.destroy();
        		System.exit(0);
        	}
        	
        	if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
        		for(Box box : shapes){
            		box.randomColors();
            		box.draw();
            	}
        	}
        	
        	for(Box box : shapes){
        		if(Mouse.isButtonDown(0) && box.inBounds(Mouse.getX(), 800 - Mouse.getY()) && !isSelected){
        			box.selected = true;
        			isSelected = true;
        		}
        		if(Mouse.isButtonDown(1)){
        			box.selected = false;
        			isSelected = false;
        		}
        		
        		if(box.selected){
        			box.update(Mouse.getDX(), -Mouse.getDY());
        		}
        		box.draw();
        	}
        	
        	
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
	
	private static class Box{
		public int x;
		public int y;
		public boolean selected = false;
		private float r,g,b;
		
		Box(int x, int y){
			this.x = x;
			this.y = y;
			randomColors();
		}
		
		void update(int dx, int dy){
			x += dx;
			y += dy;
		}
		
		boolean inBounds(int mousex, int mousey){
			if(mousex > x && mousex < x + 50 && mousey > y && mousey < y + 50)
				return true;
			else
				return false;
		}
		
		void randomColors(){
			Random randomGenerator = new Random();
			r = randomGenerator.nextFloat();
			g = randomGenerator.nextFloat();
			b = randomGenerator.nextFloat();
		}
		
		void draw(){
			glColor3f(r, g, b);
			glBegin(GL_QUADS);
				glVertex2f(x,y);
				glVertex2f(x + 50, y);
				glVertex2f(x + 50, y + 50);
				glVertex2f(x, y + 50);
			glEnd();
		}
	}
     
    public static void main(String[] argv) {
    	InputExample displayWindow = new InputExample();
        displayWindow.start();
    }
}