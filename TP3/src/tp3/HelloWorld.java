package tp3;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HelloWorld {

	// The window handle
	private long window;
	private float rotate=0.0f;
	private final int WIDTH = 300, HEIGHT = 300;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		glShadeModel(GL_SMOOTH);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		//GL2 gl = glDrawable.getGL().getGL2();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        // Set frustum :
        float fh = 0.5f;
        float aspect = (float) WIDTH / (float) HEIGHT;
        float fw = fh * aspect;
        glFrustum(-fw, fw, -fh, fh, 1.0f, 1000.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			glLoadIdentity();

			glTranslatef(0.0f, 0.0f, -5.0f);

			glRotatef(rotate, 1.0f, 0.0f, 0.0f);
       	 	glRotatef(rotate, 0.0f, 1.0f, 0.0f);
        	glRotatef(rotate, 0.0f, 0.0f, 1.0f);

			glBegin(GL_QUADS);
			glColor3f(0f,0f,1f); //Blue color  
			//Top Quadrilateral  
			glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right  
			glVertex3f( -0.5f, 0.5f, -0.5f); // Upper Left  
			glVertex3f( -0.5f, 0.5f, 0.5f ); // Bottom Left  
			glVertex3f( 0.5f, 0.5f, 0.5f ); // Bottom Right  
			//Below Quadrilateral  
			glColor3f( 1f,0f,0f ); //Red color  
			glVertex3f( 0.5f, -0.5f, 0.5f ); // Upper Right   
			glVertex3f( -0.5f, -0.5f, 0.5f ); // Upper Left   
			glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left   
			glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right   
				//Front Quadrilateral  
			glColor3f( 0f,1f,0f ); //Green color  
			glVertex3f( 0.5f, 0.5f, 0.5f ); // Upper Right   
			glVertex3f( -0.5f, 0.5f, 0.5f ); // Upper Left   
			glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Left   
			glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Right  
				//Back Quadrilateral  
			glColor3f( 1f,1f,0f ); //Yellow  
			glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Left   
			glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Right   
			glVertex3f( -0.5f, 0.5f, -0.5f ); // Upper Right   
			glVertex3f( 0.5f, 0.5f, -0.5f ); // Upper Left   
				//Left Quadrilateral  
			glColor3f( 1f,0f,1f ); //Purple  
			glVertex3f( -0.5f, 0.5f, 0.5f ); // Upper Right  
			glVertex3f( -0.5f, 0.5f, -0.5f ); // Upper Left   
			glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left   
			glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Right   
				//Right Quadrilateral  
			glColor3f( 0f,1f, 1f ); //Cyan  
			glVertex3f( 0.5f, 0.5f, -0.5f ); // Upper Right   
			glVertex3f( 0.5f, 0.5f, 0.5f ); // Upper Left   
			glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Left   
			glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right   
			glEnd();   

			rotate += 0.2f;

			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
	}

	public static void main(String[] args) {
		new HelloWorld().run();
	}

}