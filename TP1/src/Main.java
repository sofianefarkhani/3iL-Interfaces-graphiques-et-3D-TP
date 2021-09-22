import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;

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
    private JPanel verticalMenu;

    public Main() {
        frame= new JFrame("TP1");

        verticalMenu = new JPanel(new GridLayout(3,1));

        // MENUBAR
        menuBar=new JMenuBar();  
        menu=new JMenu("Menu");
        close=new JMenuItem("Close");  
        about=new JMenuItem("About");
        menu.add(close); menu.add(about);
        menuBar.add(menu);

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
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

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
    }

    public static void main(String args[])
    {
        new Main();
    }
    
}
