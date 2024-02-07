package classes.menus.gamePanels;

import classes.apples.Apple;
import classes.apples.RedApple;
import classes.snakeClasses.Direction;
import classes.snakeClasses.Firefighter;
import classes.snakeClasses.blockClasses.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicInteger;

public class SinglePlayerPanel extends GamePanel {
    private Firefighter firefighter = new Firefighter(
            Direction.RIGHT,
            GamePanel.GAME_PANEL_WIDTH / 2 / Block.BODY_SIZE * Block.BODY_SIZE,
            GamePanel.GAME_PANEL_HEIGHT / 2 / Block.BODY_SIZE * Block.BODY_SIZE);

    private final Timer ffTimer = new Timer(firefighter.getSpeed() / 10, this);
    private final Timer movingTimer = new Timer(firefighter.getSpeed() / 10, e -> firefighter.getBody().move());
    private final short TIME_FOR_STEWING = 7;
    private final AtomicInteger seconds = new AtomicInteger(TIME_FOR_STEWING);
    private Thread thread = new Thread(getTimerRunnable());

    @Override
    public void start() {
        ffTimer.start();
        thread = new Thread(getTimerRunnable());
        thread.start();
    }

    private void startMoving() {
        movingTimer.start();
    }

    @Override
    public void stopMoving() {
        movingTimer.stop();
    }

    @Override
    public void stopAll() {
        ffTimer.stop();
        movingTimer.stop();
        thread = null;
    }

    public void restartGame() {
        seconds.set(TIME_FOR_STEWING);
        isAlive = true;
        start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (isAlive) {
            graphics.drawImage(water.getAppleImage(),
                    water.getX(), water.getY(), null);

            graphics.drawImage(fire.getAppleImage(),
                    fire.getX(), fire.getY(), null);

            firefighter.getBody().draw(graphics);

            graphics.setFont(font);
            graphics.setColor(Color.green);
            graphics.drawString("Счёт: " + firefighter.getScore(), 200, 25);
            graphics.drawString("Время: " + seconds.intValue(), 600, 25);
        } else {
            graphics.setColor(Color.red);
            graphics.setFont(font);
            graphics.setFont(new Font("Calibri", Font.BOLD, 50));
            graphics.drawString("Вы проиграли", (getWidth() / 2) - 140, getHeight() / 2);
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAlive) {
            checkApple(applesList);
            firefighter.checkDeath(getWidth(), getHeight());
            checkWater();
        } else {
            removeKeyListener(listener);
        }
        repaint();
    }

    @Override
    public void checkApple(Apple... apples) {
        for (Apple apple : apples) {
            if (apple.wasAppleEaten(firefighter, apple, apples)) {
                firefighter.setSpeed((short) (firefighter.getSpeed() - 2));
                firefighter.setScore((short) (firefighter.getScore() + 1));
                seconds.set(TIME_FOR_STEWING);
                break;
            }
        }
    }

    public void checkWater() {
        firefighter.setWater(water.gotWater());
    }

    @Override
    public KeyListener setListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        startMoving();
                        firefighter.getSnake().getBody().setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        startMoving();
                        firefighter.getSnake().getBody().setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        startMoving();
                        firefighter.getSnake().getBody().setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        startMoving();
                        firefighter.getSnake().getBody().setDirection(Direction.RIGHT);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                stopMoving();
            }
        };
    }

    private Runnable getTimerRunnable() {
        return () -> {
            Thread thisThread = Thread.currentThread();
            while (seconds.intValue() > 0 && thread == thisThread) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ignore) {
                }
                seconds.set(seconds.intValue() - 1);
                isAlive = true;
            }
            if (seconds.intValue() <= 0) {
                isAlive = false;
            }
        };
    }
}
