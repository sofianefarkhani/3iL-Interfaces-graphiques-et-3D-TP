import com.jogamp.opengl.awt.GLCanvas;

public class GraphicPanel extends GLCanvas{

    public GraphicPanel(String typeOfShape) {
        switch (typeOfShape) {
            case "square":
                this.addGLEventListener(new Square());
                break;
            case "triangle":
                this.addGLEventListener(new Triangle());
                break;
            case "cube":
                this.addGLEventListener(new Cube());
                break;
        }
        this.requestFocus();
    }
}
