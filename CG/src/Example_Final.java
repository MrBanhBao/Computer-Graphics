import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import lenz.opengl.AbstractSimpleBase;
import lenz.opengl.utils.ShaderProgram;
import lenz.opengl.utils.Texture;

import org.lwjgl.input.Mouse;


public class Example_Final extends AbstractSimpleBase {
	int alpha = 0;
	ShaderProgram phong;
	Texture stoneWall;
	
	public static void main(String[] args) {
		new Example_Final().start();
	}

	@Override
	protected void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glFrustum(-1, 1, -1, 1, 1, 50);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		stoneWall = new Texture("stonewall.jpg");
		phong = new ShaderProgram("phong");
		glUseProgram(phong.getId());
	}
	
	float translate_x = 0;
	
	@Override
	protected void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		glTranslatef(0, 0,-10);
		glRotatef(30, 1,0,0);
		glRotatef(alpha, 0,1,0);
		alpha++;
		
		glTranslatef(translate_x, 0, 0);
    	
    	if (Mouse.isButtonDown(0) && Mouse.getX() > 0 && Mouse.getX() < 799) {
    		translate_x += Mouse.getDX();
    	}
		
		
		glBegin(GL_QUADS);
	      // Top face (y = 1.0f)
		  glNormal3f(0,1,0);
	      glColor3f(0.0f, 1.0f, 0.0f);     // Green
	      glVertex3f( 1.0f, 1.0f, -1.0f);
	      glVertex3f(-1.0f, 1.0f, -1.0f);
	      glVertex3f(-1.0f, 1.0f,  1.0f);
	      glVertex3f( 1.0f, 1.0f,  1.0f);
	 
	      // Bottom face (y = -1.0f)
	      glNormal3f(0,-1,0);
	      glColor3f(1.0f, 0.5f, 0.0f);     // Orange
	      glVertex3f( 1.0f, -1.0f,  1.0f);
	      glVertex3f(-1.0f, -1.0f,  1.0f);
	      glVertex3f(-1.0f, -1.0f, -1.0f);
	      glVertex3f( 1.0f, -1.0f, -1.0f);
	 
	      // Front face  (z = 1.0f)
	      glNormal3f(0,0,1);
	      glColor3f(1.0f, 0.0f, 0.0f);     // Red
	      glVertex3f( 1.0f,  1.0f, 1.0f);
	      glVertex3f(-1.0f,  1.0f, 1.0f);
	      glVertex3f(-1.0f, -1.0f, 1.0f);
	      glVertex3f( 1.0f, -1.0f, 1.0f);
	 
	      // Back face (z = -1.0f)
	      glNormal3f(0,0,-1);
	      glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
	      glVertex3f( 1.0f, -1.0f, -1.0f);
	      glVertex3f(-1.0f, -1.0f, -1.0f);
	      glVertex3f(-1.0f,  1.0f, -1.0f);
	      glVertex3f( 1.0f,  1.0f, -1.0f);
	 
	      // Left face (x = -1.0f)
	      glNormal3f(-1,0,0);
	      glColor3f(0.0f, 0.0f, 1.0f);     // Blue
	      glVertex3f(-1.0f,  1.0f,  1.0f);
	      glVertex3f(-1.0f,  1.0f, -1.0f);
	      glVertex3f(-1.0f, -1.0f, -1.0f);
	      glVertex3f(-1.0f, -1.0f,  1.0f);
	 
	      // Right face (x = 1.0f)
	      glNormal3f(1,0,0);
	      glColor3f(1.0f, 0.0f, 1.0f);     // Magenta
	      glVertex3f(1.0f,  1.0f, -1.0f);
	      glVertex3f(1.0f,  1.0f,  1.0f);
	      glVertex3f(1.0f, -1.0f,  1.0f);
	      glVertex3f(1.0f, -1.0f, -1.0f);
	   glEnd();
	   
	   glLoadIdentity();
	   glTranslatef(0, 0,-10);
	   glRotatef(30, 1,0,0);
	   glRotatef(alpha, 0,1,0);
	   glColor3f(1.0f, 1.0f, 1.0f);
	   
	   glBegin(GL_QUADS);
	      // Top face (y = 1.0f)
	   	  glNormal3f(0,1,0);
	      //glColor3f(0.0f, 1.0f, 0.0f);     // Green
	      glTexCoord2f(0,1);
	      glVertex3f( 1.0f, 1.0f, -1.0f);
	      glTexCoord2f(0,0);
	      glVertex3f(-1.0f, 1.0f, -1.0f);
	      glTexCoord2f(1,0);
	      glVertex3f(-1.0f, 1.0f,  1.0f);
	      glTexCoord2f(1,1);
	      glVertex3f( 1.0f, 1.0f,  1.0f);
	 
	      // Bottom face (y = -1.0f)
	      glNormal3f(0,-1,0);
	      //glColor3f(1.0f, 0.5f, 0.0f);     // Orange
	      glTexCoord2f(0,1);
	      glVertex3f( 1.0f, -1.0f,  1.0f);
	      glTexCoord2f(0,0);
	      glVertex3f(-1.0f, -1.0f,  1.0f);
	      glTexCoord2f(1,0);
	      glVertex3f(-1.0f, -1.0f, -1.0f);
	      glTexCoord2f(1,1);
	      glVertex3f( 1.0f, -1.0f, -1.0f);
	 
	      // Front face  (z = 1.0f)
	      glNormal3f(0,0,1);
	      //glColor3f(1.0f, 0.0f, 0.0f);     // Red
	      glTexCoord2f(0,1);
	      glVertex3f( 1.0f,  1.0f, 1.0f);
	      glTexCoord2f(0,0);
	      glVertex3f(-1.0f,  1.0f, 1.0f);
	      glTexCoord2f(1,0);
	      glVertex3f(-1.0f, -1.0f, 1.0f);
	      glTexCoord2f(1,1);
	      glVertex3f( 1.0f, -1.0f, 1.0f);
	 
	      // Back face (z = -1.0f)
	      glNormal3f(0,0,-1);
	      //glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
	      glTexCoord2f(0,1);
	      glVertex3f( 1.0f, -1.0f, -1.0f);
	      glTexCoord2f(0,0);
	      glVertex3f(-1.0f, -1.0f, -1.0f);
	      glTexCoord2f(1,0);
	      glVertex3f(-1.0f,  1.0f, -1.0f);
	      glTexCoord2f(1,1);
	      glVertex3f( 1.0f,  1.0f, -1.0f);
	 
	      // Left face (x = -1.0f)
	      glNormal3f(-1,0,0);
	      //glColor3f(0.0f, 0.0f, 1.0f);     // Blue
	      glTexCoord2f(0,1);
	      glVertex3f(-1.0f,  1.0f,  1.0f);
	      glTexCoord2f(0,0);
	      glVertex3f(-1.0f,  1.0f, -1.0f);
	      glTexCoord2f(1,0);
	      glVertex3f(-1.0f, -1.0f, -1.0f);
	      glTexCoord2f(1,1);
	      glVertex3f(-1.0f, -1.0f,  1.0f);
	 
	      // Right face (x = 1.0f)
	      glNormal3f(1,0,0);
	      //glColor3f(1.0f, 0.0f, 1.0f);     // Magenta
	      glTexCoord2f(0,1);
	      glVertex3f(1.0f,  1.0f, -1.0f);
	      glTexCoord2f(0,0);
	      glVertex3f(1.0f,  1.0f,  1.0f);
	      glTexCoord2f(1,0);
	      glVertex3f(1.0f, -1.0f,  1.0f);
	      glTexCoord2f(1,1);
	      glVertex3f(1.0f, -1.0f, -1.0f);
	      glEnd();
	  
	}
}
