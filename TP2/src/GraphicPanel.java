import com.jogamp.opengl.awt.GLCanvas;

public class GraphicPanel extends GLCanvas{

    public GraphicPanel() {
        this.addGLEventListener(new Square());
        this.requestFocus();
    }
}
