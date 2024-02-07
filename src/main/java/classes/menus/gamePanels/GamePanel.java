package classes.menus.gamePanels;

import classes.apples.Apple;
import classes.apples.RedApple;
import classes.other.Audio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public abstract class GamePanel extends JPanel implements ActionListener {
    public static int GAME_PANEL_WIDTH = 800;
    public static int GAME_PANEL_HEIGHT = 600;

    protected Apple fire = new RedApple(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT,
            new ImageIcon("src/main/resources/files/sprites/apples/fire.png"));

    protected Apple water = new RedApple(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT,
            new ImageIcon("src/main/resources/files/sprites/apples/water.png"));

    private final Audio mainSoundtrack = new Audio("src/main/resources/files/sound/soundtrack.wav");

    protected final Apple[] applesList = {water, fire};
    protected final Font font = new Font("Calibri", Font.BOLD, 25);
    protected boolean isAlive = true;
    private final JButton exitToMenu = new JButton("Главное меню");
    protected final KeyListener listener = setListener();

    public GamePanel() {
        setLayout(null);
        add(exitToMenu);

        exitToMenu.setFont(new Font("Calibri", Font.BOLD, 15));
        exitToMenu.setBounds(10, 7, 130, 25);
        exitToMenu.setBackground(Color.YELLOW);

        setBackground(new Color(59, 59, 59, 255));
        addKeyListener(listener);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setExitListener(ActionListener listener) {
        exitToMenu.addActionListener(listener);
    }

    public abstract void start();

    public abstract void stopMoving();

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public abstract void checkApple(Apple... apples);

    public void playSound() {
        mainSoundtrack.play();
    }

    public void stopSound() {
        mainSoundtrack.stop();
    }

    @Override
    public int getWidth() {
        return GAME_PANEL_WIDTH;
    }

    @Override
    public int getHeight() {
        return GAME_PANEL_HEIGHT;
    }

    public abstract KeyListener setListener();

    public abstract void stopAll();
}
