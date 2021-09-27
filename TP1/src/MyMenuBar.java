import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class MyMenuBar extends JMenuBar{
    private JMenu menu;
    private JMenuItem close, about;

    public MyMenuBar() {
        menu=new JMenu("Menu");
        about=new JMenuItem("A propos");
        close=new JMenuItem("Fermer");  
        menu.add(about); menu.add(close);
        this.add(menu);
    }

    public JMenuItem getCloseItem() {
        return close;
    }

    public JMenuItem getAboutItem() {
        return about;
    }
}
