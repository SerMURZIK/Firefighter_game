package classes.menus.restartPanels;

import classes.menus.ThePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RestartPanel extends ThePanel {
    private final JButton restart = new JButton("Заново");
    protected final Font font = new Font("Calibri", Font.BOLD, 20);

    public RestartPanel() {
        JButton exit = new JButton("Выйти");

        add(restart, 0);
        add(exit, 1);

        restart.setFont(font);
        restart.setBackground(Color.CYAN);
        restart.setBounds(530, 300, 100, 50);

        exit.setFont(font);
        exit.setBackground(theColor);
        exit.setBounds(530, 370, 100, 50);

        exit.addActionListener(e -> System.exit(0));
    }

    public void setRestartListener(ActionListener listener) {
        restart.addActionListener(listener);
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
