import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import lenz.opengl.AbstractSimpleBase;
import lenz.opengl.utils.ShaderProgram;
import lenz.opengl.utils.Texture;

public class ExampleHao extends AbstractSimpleBase {
	int alpha = 0;
	ShaderProgram phong;
	Texture stoneWall;
	
	public static void main(String[] args) {
		new ExampleHao().start();
	}

	@Override
	protected void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
//		glOrtho(-500, 500, -500, 500, 0, 1);
		glFrustum(-1, 1, -1, 1, 1, 50);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_FLAT);
		glShadeModel(GL_SMOOTH);
		stoneWall = new Texture("stonewall.jpg");
		phong = new ShaderProgram("phong");
		glUseProgram(phong.getId());
	}
	
	@Override
	protected void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		glTranslated(0,0,-5);
		glRotatef(alpha, 0,1,1);
		alpha++;

		
		glBegin(GL_QUADS);
		
		//glColor3f(0.0f,1.0f,0.0f);   //Up
		glNormal3f(0, 1, 0);
		glTexCoord2f(1,0);
		glVertex3i( 1, 1,-1); 
		glTexCoord2f(0,0);
		glVertex3i(-1, 1,-1);
		glTexCoord2f(1,1);
		glVertex3i(-1, 1, 1);
		glTexCoord2f(0,1);
        glVertex3i( 1, 1, 1);                  
  
        //glColor3f(1.0f,0.5f,0.0f);  //Down
		glNormal3f(0, -1, 0);
        glTexCoord2f(1,0);
        glVertex3i( 1,-1, 1);
        glTexCoord2f(0,0);
        glVertex3i(-1,-1, 1);
        glTexCoord2f(1,1);
        glVertex3i(-1,-1,-1);
        glTexCoord2f(0,1);
        glVertex3i( 1,-1,-1); 
                    
        //glColor3f(1.0f,0.0f,0.0f);	//FRONT
        glNormal3f(0, 0, 1);
        glTexCoord2f(1,0);
        glVertex3i( 1, 1, 1);
        glTexCoord2f(0,0);
        glVertex3i(-1, 1, 1);
        glTexCoord2f(1,1);
        glVertex3i(-1,-1, 1);
        glTexCoord2f(0,1);
        glVertex3i( 1,-1, 1);                  
  
        //glColor3f(1.0f,1.0f,0.0f);	//Back
        glNormal3f(0, 0, -1);
        glTexCoord2f(1,0);
        glVertex3i( 1,-1,-1);
        glTexCoord2f(0,0);
        glVertex3i(-1,-1,-1);
        glTexCoord2f(1,1);
        glVertex3i(-1, 1,-1);
        glTexCoord2f(0,1);
        glVertex3i( 1, 1,-1);                  
  
        //glColor3f(0.0f,0.0f,1.0f);	//Left
        glNormal3f(-1, 0, 0);
        glTexCoord2f(1,0);
        glVertex3i(-1, 1, 1);
        glTexCoord2f(0,0);
        glVertex3i(-1, 1,-1);
        glTexCoord2f(1,1);
        glVertex3i(-1,-1,-1);
        glTexCoord2f(0,1);
        glVertex3i(-1,-1, 1);                  
  
        //Color3f(1.0f,0.0f,1.0f);	//Right
        glNormal3f(1, 0, 0);
        glTexCoord2f(1,0);
        glVertex3i( 1, 1,-1);
        glTexCoord2f(0,0);
        glVertex3i( 1, 1, 1);
        glTexCoord2f(1,1);
        glVertex3i( 1,-1, 1);
        glTexCoord2f(0,1);
        glVertex3i( 1,-1,-1);
		
        glEnd();
		
	}
}