import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.io.File;

public class MyFrame extends JFrame{

    private final File PATH_IMG_DEFAULT = new File("TP1/images/image1.jpg");
    private File pathImg=PATH_IMG_DEFAULT;

    private VerticalMenuPanel verticalMenuPanel = new VerticalMenuPanel();
    private ImagePanel imagePanel;
    private AppAction appAction;

    private MyMenuBar menuBar;
    private JMenu menu;
    private JMenuItem close, about;

    public MyFrame(String title, int width, int height) {

        // MENUBAR

        imagePanel = new ImagePanel(pathImg.getAbsolutePath());
        menuBar = new MyMenuBar();

        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(verticalMenuPanel,BorderLayout.LINE_START);
        this.setPreferredSize(new Dimension(width, height));
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.pack();

        appAction = new AppAction(this, imagePanel);

        menuBar.getCloseItem().addActionListener(appAction);
        menuBar.getAboutItem().addActionListener(appAction);

        verticalMenuPanel.getLoadBtn().addActionListener(appAction);
        verticalMenuPanel.getDelBtn().addActionListener(appAction);
    }
}