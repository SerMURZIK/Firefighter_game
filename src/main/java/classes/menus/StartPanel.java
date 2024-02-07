package classes.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends ThePanel {
    private final JButton start = new JButton("Начать");

    public StartPanel() {
        JButton closeGame = new JButton("Выйти");
        Font font = new Font("Calibri", Font.BOLD, 20);

        add(start, 0);
        add(closeGame, 1);

        start.setFont(font);
        closeGame.setFont(font);

        start.setBounds(530, 300, 100, 50);
        start.setBackground(Color.CYAN);

        closeGame.setBounds(530, 370, 150, 50);
        closeGame.setBackground(theColor);
        closeGame.addActionListener(e -> System.exit(0));
        start.setFocusable(false);
        closeGame.setFocusable(false);
    }

    public void setStartListener(ActionListener lister) {
        start.addActionListener(lister);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
