import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.jogamp.opengl.util.Animator;

public class MyFrame extends JFrame{

    private MyMenuBar menuBar;
    private LateralMenu lateralMenu;
    private AppAction appAction;
    private GraphicPanel graphicPanel;

    private Animator animator;

    public MyFrame(String title, int width, int height, int menuWidth, int menuHeight) {
        
        lateralMenu = new LateralMenu(menuWidth, menuHeight);
        menuBar = new MyMenuBar();
        graphicPanel = new GraphicPanel("triangle");

        animator = new Animator(graphicPanel);
        animator.start();

        appAction = new AppAction(this, animator);


        menuBar.getCloseItem().addActionListener(appAction);
        menuBar.getAboutItem().addActionListener(appAction);
        lateralMenu.getCubeRGB().addActionListener(appAction);
        lateralMenu.getTriangleRGB().addActionListener(appAction);
        lateralMenu.getReset().addActionListener(appAction);
        lateralMenu.getRotateBtn().addActionListener(appAction);

        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.add(lateralMenu, BorderLayout.LINE_END);
        this.add(graphicPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(width, height));
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.pack();
    }
}