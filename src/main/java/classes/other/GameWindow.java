package classes.other;

import classes.menus.MainPanel;
import classes.menus.StartPanel;
import classes.menus.TabWithControlButtons;
import classes.menus.gamePanels.GamePanel;
import classes.menus.gamePanels.SinglePlayerPanel;
import classes.menus.restartPanels.RestartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow {
    private final JFrame window = new JFrame();

    private SinglePlayerPanel singlePlayer;
    private final MainPanel mainPanel = new MainPanel();
    private final StartPanel startPanel = new StartPanel();
    private final RestartPanel loadingRestartPanel = new RestartPanel();
    private final TabWithControlButtons tabWithControlButtons = new TabWithControlButtons();

    public GameWindow() {
        singlePlayer = new SinglePlayerPanel();

        addSinglePlayerKeyListener();

        window.setResizable(false);
        window.setUndecorated(true);
        window.setSize(new Dimension(startPanel.getWidth(), startPanel.getHeight()));
        window.setName("Firefighter");
        window.setLocationRelativeTo(null);
        window.add(startPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMainMenuListeners();
        addRestartPanelListener();
        addStartPanelListener();
        addTabCtrlButtListener();
    }

    private void addSinglePlayerKeyListener() {
        singlePlayer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ESCAPE) {
                    openGamePanel(singlePlayer, loadingRestartPanel);
                }
            }
        });
    }

    private void addStartPanelListener() {
        startPanel.setStartListener(e -> changePanel(startPanel, mainPanel));
    }

    private void addTabCtrlButtListener() {
        tabWithControlButtons.setBackListener(e -> changePanel(tabWithControlButtons, mainPanel));
    }

    private void setOpeningListeners() {
        singlePlayer.setExitListener(e -> {openGamePanel(singlePlayer, loadingRestartPanel);});
        addSinglePlayerKeyListener();
    }

    public void openGamePanel(GamePanel gamePanel, RestartPanel restartPanel) {
        gamePanel.stopAll();
        singlePlayer.stopSound();
        window.remove(gamePanel);
        if (gamePanel.isAlive()) {
            window.add(mainPanel);
            window.setSize(new Dimension(mainPanel.getWidth(), mainPanel.getHeight()));
        } else {
            window.add(restartPanel);
            window.setSize(new Dimension(restartPanel.getWidth(), restartPanel.getHeight()));
        }
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.repaint();
    }

    public void changePanel(JPanel removePanel, JPanel newPanel) {
        window.remove(removePanel);
        window.add(newPanel);
        window.setSize(new Dimension(newPanel.getWidth(), newPanel.getHeight()));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void addMainMenuListeners() {
        mainPanel.setStartListener(e -> {
            changePanel(mainPanel, singlePlayer);
            singlePlayer.start();
            singlePlayer.playSound();
            singlePlayer.requestFocus();
        });

        mainPanel.setTeachingPanelListener(e -> changePanel(mainPanel, tabWithControlButtons));
    }

    public void addRestartPanelListener() {
        loadingRestartPanel.setRestartListener(e -> {
            singlePlayer = new SinglePlayerPanel();
            changePanel(loadingRestartPanel, singlePlayer);
            setOpeningListeners();
            singlePlayer.restartGame();
            singlePlayer.requestFocus();
            window.repaint();
        });

        setOpeningListeners();
    }
}
