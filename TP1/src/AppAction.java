import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JFileChooser;

import java.io.File;

public class AppAction implements ActionListener {

    private final File PATH_IMG_DEFAULT = new File("TP1/images/image1.jpg");

    private JFrame frame;
    private File pathImg;
    private ImagePanel imagePanel;

    public AppAction(JFrame frame, ImagePanel imagePanel)
    {
        this.frame = frame;
        this.imagePanel = imagePanel;
    }

    public void actionPerformed(ActionEvent evt) {
        String composant = evt.getActionCommand();
        switch(composant) {
            case "Fermer":
                System.exit(0);
                break;
            case "A propos":
                JOptionPane.showMessageDialog(null, "A propos du TP1");
                break;
            case "Charger une image":
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setDialogTitle("Charger une image");
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                file.addChoosableFileFilter(new ImageFilter());
                int res = file.showOpenDialog(null);
                if(res == JFileChooser.APPROVE_OPTION){
                    pathImg=file.getSelectedFile();
                    frame.remove(imagePanel);
                    imagePanel = new ImagePanel(pathImg.getAbsolutePath());
                    frame.add(imagePanel, BorderLayout.CENTER);
                    frame.pack();
                }
                break;
            case "Supprimer l'image":
                pathImg=PATH_IMG_DEFAULT;
                frame.remove(imagePanel);
                imagePanel = new ImagePanel(pathImg.getAbsolutePath());
                frame.add(imagePanel, BorderLayout.CENTER);
                frame.pack();
                break;
        }
    }

}
