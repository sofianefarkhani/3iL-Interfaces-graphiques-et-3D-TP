import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

    // CONSTANTES
    public static final int FRAME_WIDTH = 1168;
    public static final int FRAME_HEIGHT = 800;

    // ATTRIBUTS
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem close, about;
    private JButton loadBtn, delBtn, moveBtn;
    private JPanel verticalMenu, displayImg;
    private File pathImg1, pathImg2;
    private Image image;

    public Main() {
        frame= new JFrame("TP1");

        pathImg1 = new File("TP1/images/image1.jpg");
        pathImg2 = new File("TP1/images/image2.jpg");

        verticalMenu = new JPanel(new GridLayout(3,1));
        loadImage(pathImg1);
        displayImg = new ImagePanel(image);

        // MENUBAR
        menuBar=new JMenuBar();  
        menu=new JMenu("Menu");
        close=new JMenuItem("Close");  
        about=new JMenuItem("About");
        menu.add(close); menu.add(about);
        menuBar.add(menu);

        // VERTICAL MENU
        loadBtn = new JButton("Charger une image");
        delBtn = new JButton("Supprimer l'image");
        moveBtn = new JButton("Déplacer l'image");
        verticalMenu.add(loadBtn);
        verticalMenu.add(delBtn);
        verticalMenu.add(moveBtn);

        frame.setJMenuBar(menuBar);  
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);  
        frame.setLayout(new BorderLayout()); 
        frame.setVisible(true); 
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        frame.add(displayImg, BorderLayout.CENTER);
        frame.add(verticalMenu, BorderLayout.LINE_START);
        
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "TP1");
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
                file.addChoosableFileFilter(filter);
                int res = file.showSaveDialog(null);
                if(res == JFileChooser.APPROVE_OPTION){
                    pathImg2 = file.getSelectedFile();
                    loadImage(pathImg2);
                    frame.add(new ImagePanel(image), BorderLayout.CENTER);
                    frame.pack();
                }
                
            }
        });

        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadImage(pathImg1);
                frame.add(new ImagePanel(image), BorderLayout.CENTER);
                frame.pack();
            }
        });
    }

    private boolean loadImage(File path)
    {
        try {
            image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String args[])
    {
        new Main();
    }
    
}
