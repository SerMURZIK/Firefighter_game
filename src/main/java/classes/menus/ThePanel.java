package classes.menus;

import javax.swing.*;
import java.awt.*;

public abstract class ThePanel extends JPanel {
    protected final int width = 800, height = 600;
    protected final Color theColor = new Color(15, 227, 15);

    public ThePanel() {
        JLabel fon = new JLabel(new ImageIcon("src/main/resources/files/sprites/other/fon.png"));
        fon.setBounds(0, 0, width, height);
        setLayout(null);
        add(fon);
    }
}
