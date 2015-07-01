import static org.lwjgl.opengl.GL11.*;
import lenz.opengl.AbstractSimpleBase;

public class Example extends AbstractSimpleBase {
	int gamma = 0;
	int alpha = 360;
	int beta = 0;
	int sigma = 360;
	public static void main(String[] args) {
		new Example().start();
	}

	@Override
	protected void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
//		glOrtho(-500, 500, -500, 500, 0, 1);
		glFrustum(-1, 1, -1, 1, 1, 500);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		//glShadeModel(GL_FLAT);
		glShadeModel(GL_SMOOTH);
	}
	
	@Override
	protected void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		glTranslated(0,100,-350);
		glRotatef(30, 1,0,0);
		glRotatef(alpha, 0,1,0);
		alpha++;
		
//		glRotatef(beta, 0,1,0);
//		beta++;
		
//		glRotatef(gamma, 1,0,0);
//		gamma++;
		

		// !!! - WÜRFEL - !!!
		glBegin(GL_QUADS);
		
		
		glColor3f(0.0f,1.0f,0.0f);   //Up             
		glVertex3i( 50, 50,-50);                  
		glVertex3i(-50, 50,-50);                  
		glVertex3i(-50, 50, 50);                  
        glVertex3i( 50, 50, 50);                  
  
        glColor3f(1.0f,0.5f,0.0f);  //Down                    
        glVertex3i( 50,-50, 50);                  
        glVertex3i(-50,-50, 50);                  
        glVertex3i(-50,-50,-50);                  
        glVertex3i( 50,-50,-50); 
                    
        glColor3f(1.0f,0.0f,0.0f);	//FRONT                  
        glVertex3i( 50, 50, 50);                  
        glVertex3i(-50, 50, 50);                  
        glVertex3i(-50,-50, 50);                  
        glVertex3i( 50,-50, 50);                  
  
        glColor3f(1.0f,1.0f,0.0f);	//Back                  
        glVertex3i( 50,-50,-50);              
        glVertex3i(-50,-50,-50);                  
        glVertex3i(-50, 50,-50);                  
        glVertex3i( 50, 50,-50);                  
  
        glColor3f(0.0f,0.0f,1.0f);	//Left                 
        glVertex3i(-50, 50, 50);                  
        glVertex3i(-50, 50,-50);                  
        glVertex3i(-50,-50,-50);                  
        glVertex3i(-50,-50, 50);                  
  
        glColor3f(1.0f,0.0f,1.0f);	//Right                  
        glVertex3i( 50, 50,-50);                  
        glVertex3i( 50, 50, 50);                  
        glVertex3i( 50,-50, 50);                  
        glVertex3i( 50,-50,-50);
        
        glEnd();

/*
        glBegin( GL_TRIANGLES );
        glColor3f( 1.0f, 0.0f, 0.0f );
        glVertex3f(0, 50, 0);
    	glColor3f( 0.0f, 1.0f, 0.0f );
    	glVertex3f(-50, -50, 50);
    	glColor3f( 0.0f, 0.0f, 1.0f );
    	glVertex3f( 50, -50, 50);

    	glColor3f( 1.0f, 0.0f, 0.0f );
    	glVertex3f( 0, 50, 0);
    	glColor3f( 0.0f, 0.0f, 1.0f );
    	glVertex3f( 0, -50, -50);
    	glColor3f( 0.0f, 1.0f, 0.0f );
    	glVertex3f( -50, -50, 50);

    	glColor3f( 1.0f, 0.0f, 0.0f );
    	glVertex3f( 0, 50, 0);
    	glColor3f( 0.0f, 0.0f, 1.0f );
    	glVertex3f( 50, -50, 50);
    	glColor3f( 0.0f, 1.0f, 0.0f );
    	glVertex3f( 0, -50, -50);


    	glColor3f( 1.0f, 0.0f, 0.0f );
    	glVertex3f( -50, -50, 50);
    	glColor3f( 0.0f, 1.0f, 0.0f );
    	glVertex3f( 0, -50, -50);
    	glColor3f( 0.0f, 0.0f, 1.0f );
    	glVertex3f( 50, -50, 50);

    	glEnd();
*/
	}

	

/*	//2D
	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		glBegin(GL_LINES);
		
		glColor3f(1, 0, 0);
		glVertex2i(0,0);
		glVertex2i(0,500);
		
		glColor3f(0, 1, 0);
		glVertex2i(0,0);
		glVertex2i(0,-500);
		
		glColor3f(0, 0, 1);
		glVertex2i(0,0);
		glVertex2i(-500,0);
		
		glColor3f(1,0,1);
		glVertex2i(0,0);
		glVertex2i(500,0);
				
		glEnd();
		
		//glTranslated(0,20,0);
		//glScalef(2,2,1);
		glRotatef(gamma, 0,0,1);
		gamma++;
		
		glBegin(GL_QUADS);
		glColor3f(1, 0.5f, 0);
		glVertex2i(125,125);
		glVertex2i(125, 375);
		glVertex2i(375, 375);
		glVertex2i(375,125);
		glEnd();
		
		glLoadIdentity();
		
		glRotatef(alpha, 0,0,1);
		alpha-=2;
		glBegin(GL_TRIANGLES);
		glColor3f(0, 1, 1);
		glVertex2i(125,-125);
		glVertex2i(250, -375);
		glVertex2i(375,-125);
		glEnd();
		
		glLoadIdentity();
		
		glRotatef(sigma, 0,0,1);
		sigma-=2;
		glBegin(GL_POLYGON);
		glColor3f(0, 0.5f, 1);
		glVertex2i(-125, 250);
		glVertex2i(-250, 375);
		glVertex2i(-375, 250);
		glVertex2i(-250,125);
		glEnd();
		
		glLoadIdentity();
		
		glRotatef(gamma, 0,0,1);
		beta++;
		glBegin(GL_QUADS);
		glColor3f(1, 1, 0);
		glVertex2i(-125,-125);
		glVertex2i(-125, -375);
		glVertex2i(-375, -375);
		glVertex2i(-375,-125);
		glEnd();
	}
	*/
}
