import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES1;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class Cube implements GLEventListener {

    private float rotate;

    public Cube() {
        rotate = 0.0f;
    }

    @Override
    public void init(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
    }
    
    @Override
    public void reshape(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();

        // Set frustum :
        float fh = 0.5f;
        float aspect = (float) width / (float) height;
        float fw = fh * aspect;
        gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 1000.0f);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable glDrawable) {
        final GL2 gl = glDrawable.getGL().getGL2();

        // Remove last render :
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Apply a translation to the current position :
        gl.glTranslatef(0.0f, 0.0f, -5.0f);

        // Apply a rotation to the current position :
        gl.glRotatef(rotate, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(rotate, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(rotate, 0.0f, 0.0f, 1.0f);

        // Draw cube
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f); //Blue color  
        //Top Quadrilateral  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right  
        gl.glVertex3f( -0.5f, 0.5f, -0.5f); // Upper Left  
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Bottom Left  
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Bottom Right  
        //Below Quadrilateral  
        gl.glColor3f( 1f,0f,0f ); //Red color  
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Upper Right   
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Upper Left   
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left   
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right   
            //Front Quadrilateral  
        gl.glColor3f( 0f,1f,0f ); //Green color  
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Upper Right   
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Upper Left   
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Left   
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Right  
            //Back Quadrilateral  
        gl.glColor3f( 1f,1f,0f ); //Yellow  
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Left   
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Right   
        gl.glVertex3f( -0.5f, 0.5f, -0.5f ); // Upper Right   
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Upper Left   
            //Left Quadrilateral  
        gl.glColor3f( 1f,0f,1f ); //Purple  
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Upper Right  
        gl.glVertex3f( -0.5f, 0.5f, -0.5f ); // Upper Left   
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left   
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Right   
            //Right Quadrilateral  
        gl.glColor3f( 0f,1f, 1f ); //Cyan  
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Upper Right   
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Upper Left   
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Left   
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right   
        gl.glEnd();   

        // Increase rotation :
        rotate += 0.2f;
        
    }

    @Override
    public void dispose(GLAutoDrawable glDrawable) {
    }
    
}
