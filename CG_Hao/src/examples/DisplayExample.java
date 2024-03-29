package examples;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DisplayExample {
    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.setTitle("Hello, World!");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
         
        // init OpenGL here
         
        while (!Display.isCloseRequested()) {
             
            // render OpenGL here
             
            Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
    }
     
    public static void main(String[] argv) {
    	DisplayExample displayWindow = new DisplayExample();
        displayWindow.start();
    }
}
