package classes.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TabWithControlButtons extends JPanel {
    private final JButton back = new JButton("Назад");

    public TabWithControlButtons() {
        Font font = new Font("Calibri", Font.PLAIN, 30);

        JLabel teachingText = new JLabel("<html>Нажимайте <b>стрелочки</b> для передвижения<br>" +
                "Нажимайте <b>ESC</b> чтобы открыть главное меню</html>");

        add(teachingText);
        add(back);

        teachingText.setFont(font);

        back.setFont(font);
        back.setBackground(Color.WHITE);

        repaint();
    }

    public void setBackListener(ActionListener listener) {
        back.addActionListener(listener);
    }

    @Override
    public int getWidth() {
        return 650;
    }

    @Override
    public int getHeight() {
        return 150;
    }
}
