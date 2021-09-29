import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES1;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class Triangle implements GLEventListener {

    private float rotate;

    public Triangle() {
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

        // Draw triangle
        gl.glBegin(GL2.GL_TRIANGLE_STRIP);
        gl.glColor3f(0.0f, 1.0f, 0f);  
        gl.glVertex3f(-1.0f, 1.0f, 0.0f); // Set a point at top left
        gl.glColor3f(1f,0f,0f);   
        gl.glVertex3f(1.0f, 1.0f, 0.0f); // Set a point at top right
        gl.glColor3f(0f,0f,1f);   
        gl.glVertex3f(1.0f, -1.0f, 0.0f); // Set a point at bottom right
        gl.glEnd();

        // Increase rotation :
        rotate += 0.2f;
        
    }

    @Override
    public void dispose(GLAutoDrawable glDrawable) {
    }
    
}
