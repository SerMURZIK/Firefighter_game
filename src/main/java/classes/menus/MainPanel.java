package classes.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends ThePanel {
    private final JButton start = new JButton("Начать");
    private final JButton teachingPanel = new JButton("Клавиши управления");

    public MainPanel() {
        Font font = new Font("Calibri", Font.BOLD, 20);
        JButton exit = new JButton("Выйти");
        exit.addActionListener(e -> System.exit(0));

        add(start, 0);
        add(teachingPanel, 1);
        add(exit, 2);

        start.setFont(font);
        start.setBackground(Color.CYAN);
        start.setBounds(520, 300, 250, 50);
        teachingPanel.setFont(font);
        teachingPanel.setBackground(Color.CYAN);
        teachingPanel.setBounds(520, 370, 250, 50);
        exit.setFont(font);
        exit.setBackground(theColor);
        exit.setBounds(520, 440, 250, 50);
    }

    public void setStartListener(ActionListener listener) {
        start.addActionListener(listener);
    }

    public void setTeachingPanelListener(ActionListener listener) {
        teachingPanel.addActionListener(listener);
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
