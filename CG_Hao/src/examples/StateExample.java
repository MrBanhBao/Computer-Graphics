package examples;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

public class StateExample {
	
	private static enum State {
		INTRO, MAIN_MENU, GAME;
	}
	
	private State state = State.INTRO;
	
	public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setTitle("Hello, GAMESTATE!");
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
        	
        	checkInput();
        	render();
        	
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
	
	public void render(){
		switch(state){
		case INTRO:
			glColor3f(1,0,0);
			glRectf(0,0,800,600);
			break;
		case GAME:
			glColor3f(0,1,0);
			glRectf(0,0,800,600);
			break;
		case MAIN_MENU:
			glColor3f(0,0,1);
			glRectf(0,0,800,600);
			break;
		}
	}
		
	public void checkInput(){
		switch(state){
		case INTRO:
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
				state = State.MAIN_MENU;
			}
			break;
		case GAME:
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				state = State.MAIN_MENU;
			}
			break;
		case MAIN_MENU:
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
				state = State.GAME;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				state = State.INTRO;
			}
			break;
		}
	}
     
    public static void main(String[] argv) {
    	StateExample displayWindow = new StateExample();
        displayWindow.start();
    }
}