import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

    // CONSTANTES
    public static final int FRAME_WIDTH = 1168;
    public static final int FRAME_HEIGHT = 800;

    // ATTRIBUTS
    private JFrame frame;

    public Main() {
        frame= new JFrame("TP1");
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);  
        frame.setLayout(new BorderLayout()); 
        frame.setVisible(true); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[])
    {
        new Main();
    }
    
}
