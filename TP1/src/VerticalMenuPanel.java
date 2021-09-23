import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;

public class VerticalMenuPanel extends JPanel{

    private JButton loadBtn, delBtn;

    public VerticalMenuPanel() {
        this.setLayout(new GridLayout(3,1));
        loadBtn = new JButton("Charger une image");
        delBtn = new JButton("Supprimer l'image");
        this.add(loadBtn);
        this.add(delBtn);
    }

    public JButton getLoadBtn() {
        return loadBtn;
    }

    public JButton getDelBtn() {
        return delBtn;
    }
}
