import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jogamp.opengl.util.Animator;

public class AppAction implements ActionListener {

    private JFrame frame;
    private Animator animator;
    private GraphicPanel graphicPanel;

    public AppAction(JFrame frame, Animator animator, GraphicPanel graphicPanel)
    {
        this.frame = frame;
        this.animator = animator;
        this.graphicPanel = graphicPanel;
    }

    public void actionPerformed(ActionEvent evt) {
        String composant = evt.getActionCommand();
        switch(composant) {
            case "Fermer":
                System.exit(0);
                break;
            case "A propos":
                JOptionPane.showMessageDialog(frame, "A propos du TP2");
                break;
            case "Cube RGB":
                graphicPanel.addGLEventListener(new Cube());
                break;
            case "Triangle RGB":
                //graphicPanel.removeGLEventListener(new Square());
                graphicPanel.addGLEventListener(new Triangle());
                break;
            case "Reset":
                //graphicPanel.removeGLEventListener(new Square());
                graphicPanel.addGLEventListener(new Square());
                break;
            case "Start/Stop rotation":
                if (animator.isStarted())
                    animator.stop();
                else
                    animator.start();
                break;
        }
    }

}
