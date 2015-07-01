package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class TimerExample {
	
	//Frameunabhängige Bewegungen
	private long lastFrame;
	private long getTime(){
		return(Sys.getTime()*1000)/Sys.getTimerResolution();
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setTitle("Hello, World!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        
        int y = 100; //Eckpunkte des gezeichneten Qaudrads
        int x = 100;
        int dx = 1; //Schritte, die Quad wandert
        int dy = 1;
        
        // init OpenGL here
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
        lastFrame = getTime();
        
        while (!Display.isCloseRequested()) {
            // render OpenGL here
        	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        	
        	int delta = getDelta();
        	x += delta * dx * 0.5;
        	y += delta * dy * 0.3;
        	
        	glRecti(x , y, x+100, y+100);
        	
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
     
    public static void main(String[] argv) {
    	TimerExample displayWindow = new TimerExample();
        displayWindow.start();
    }
}
