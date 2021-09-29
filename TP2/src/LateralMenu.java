import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Dimension;

public class LateralMenu extends JPanel {
    
    private JButton cubeRGBBtn, triangleRGBBtn, resetBtn, rotateBtn;

    public LateralMenu(int menuWidth, int menuHeight) {
        this.setPreferredSize(new Dimension(menuWidth, menuHeight));
        this.setSize(new Dimension(menuWidth, menuHeight));
        this.setLayout(new GridLayout(4,1));
        cubeRGBBtn = new JButton("Cube RGB");
        triangleRGBBtn = new JButton("Triangle RGB");
        resetBtn = new JButton("Reset");
        rotateBtn = new JButton("Start/Stop rotation");
        this.add(cubeRGBBtn);
        this.add(triangleRGBBtn);
        this.add(resetBtn);
        this.add(rotateBtn);
    }

    public JButton getCubeRGB() {
        return cubeRGBBtn;
    }

    public JButton getTriangleRGB() {
        return triangleRGBBtn;
    }

    public JButton getReset() {
        return resetBtn;
    }

    public JButton getRotateBtn() {
        return rotateBtn;
    }

}
