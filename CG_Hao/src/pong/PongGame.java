package pong;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import entities.AbstractMoveableEntity;

public class PongGame {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private boolean isRunning = true;
	private long lastFrame;
	private Ball ball;
	private static Bat bat1;
	private static Bat bat2;
	
	private long getTime(){
		return(Sys.getTime()*1000)/Sys.getTimerResolution();
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public PongGame() {
		// TODO Auto-generated constructor stub
		setUpDisplay();
		setUpOpenGL();
		setUpEntities();
		setUpTime();
		while(isRunning){
			render();
			logic(getDelta());
			input();
			Display.update();
			Display.sync(60);
			if(Display.isCloseRequested()){
				isRunning = false;
			}
		}
		Display.destroy();
		System.exit(0);
	}
	
	private void setUpDisplay(){
		try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.setTitle("Pong Game");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
	}
	
	private void setUpOpenGL(){
		glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
	}
	
	private void setUpEntities(){
		bat1 = new Bat(10, HEIGHT/2 - 80/2, 10, 80);
		bat2 = new Bat(WIDTH-20, HEIGHT/2 - 80/2, 10, 80);
		ball = new Ball(WIDTH/2-10/2, HEIGHT/2-10/2, 10, 10);
		ball.setDX(-.1);
	}
	
	private static void input() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            bat1.setDY(-.2);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            bat1.setDY(.2);
        } else {
            bat1.setDY(0);
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            bat2.setDY(-.2);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            bat2.setDY(.2);
        } else {
            bat2.setDY(0);
        }
    }
	
	private void setUpTime(){
		lastFrame = getTime();
	}
	
	private void render(){
    	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		ball.draw();
		bat1.draw();
		bat2.draw();
	}
	
	private void logic(int delta){
		ball.update(delta);
		bat1.update(delta);
		bat2.update(delta);
		if(ball.getX() <= bat1.getX() + bat1.getWidth() && ball.getX() >= bat1.getX() && ball.getY() >= bat1.getY() &&
                ball.getY() <= bat1.getY() + bat1.getHeight()){
			ball.setDX(0.3);
		}
		
		if(ball.getX() <= bat2.getX() + bat2.getWidth() && ball.getX() >= bat2.getX() && ball.getY() >= bat2.getY() &&
                ball.getY() <= bat2.getY() + bat2.getHeight()){
			ball.setDX(-0.3);
		}
	}

	private static class Bat extends AbstractMoveableEntity{

		public Bat(double x, double y, double width, double height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void draw() {
			glRectd(x, y, x + width, y + height);
		}
	}
	
	private static class Ball extends AbstractMoveableEntity{

		public Ball(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		@Override
		public void draw() {
			glRectd(x, y, x + width, y + height);
		}	
	}
	
	public static void main(String[] args){
		new PongGame();
	}

}