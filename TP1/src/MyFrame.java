import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;

public class MyFrame extends JFrame{

    private final File PATH_IMG_DEFAULT = new File("TP1/images/image1.jpg");
    private File pathImg=PATH_IMG_DEFAULT;

    private VerticalMenuPanel verticalMenuPanel = new VerticalMenuPanel();
    private ImagePanel imagePanel;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem close, about;

    public MyFrame(String title, int width, int height) {

        // MENUBAR
        createMenuBar();

        imagePanel = new ImagePanel(pathImg.getAbsolutePath());

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

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "A propos du TP1");
            }
        });

        verticalMenuPanel.loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
                file.addChoosableFileFilter(filter);
                int res = file.showSaveDialog(null);
                if(res == JFileChooser.APPROVE_OPTION){
                    pathImg=file.getSelectedFile();
                    remove(imagePanel);
                    imagePanel = new ImagePanel(pathImg.getAbsolutePath());
                    add(imagePanel, BorderLayout.CENTER);
                    pack();
                }
                
            }
        });

        verticalMenuPanel.delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pathImg=PATH_IMG_DEFAULT;
                remove(imagePanel);
                imagePanel = new ImagePanel(pathImg.getAbsolutePath());
                add(imagePanel, BorderLayout.CENTER);
                pack();
            }
        });
    }

    private void createMenuBar() {
        menuBar=new JMenuBar();  
        menu=new JMenu("Menu");
        about=new JMenuItem("A propos");
        close=new JMenuItem("Fermer");  
        menu.add(about); menu.add(close);
        menuBar.add(menu);
    }

}