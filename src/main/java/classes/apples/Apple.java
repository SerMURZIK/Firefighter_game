package classes.apples;

import classes.other.Audio;
import classes.snakeClasses.Firefighter;
import classes.snakeClasses.blockClasses.Block;

import javax.swing.*;
import java.awt.*;

public abstract class Apple {
    private int x, y;
    protected int windowWidth, windowHeight;
    private final ImageIcon appleIco;
    protected boolean gotWater = false;
    private final Audio audio = new Audio("src/main/resources/files/sound/water.wav");

    public Apple(int windowWidth, int windowHeight, ImageIcon appleIco) {
        this.appleIco = appleIco;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public boolean wasAppleEaten(Firefighter firefighter, Apple apple, Apple... apples) {
        if (firefighter.getBody().getX() <= apple.getX() + Block.BODY_SIZE &&
                firefighter.getBody().getX() > apple.getX() - Block.BODY_SIZE &&
                firefighter.getBody().getY() <= apple.getY() + Block.BODY_SIZE &&
                firefighter.getBody().getY() > apple.getY() - Block.BODY_SIZE) {
            for (Apple apple1 : apples) {
                if (apple1.gotWater) {
                    createApple();
                    apples[0].gotWater = false;
                    apples[0].createApple();
                    apples[1].createApple();
                    new Thread(getAudioRunnable()).start();
                    return true;
                } else if (apple.equals(apples[0])) {
                    apples[0].setX(-100);
                    apples[0].setY(-100);
                    apples[0].gotWater = true;
                    return false;
                }
            }
        }
        return false;
    }

    private Runnable getAudioRunnable() {
        return () -> {
            try {
                audio.play();
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                Thread.currentThread().interrupt();
                audio.stop();
            }
        };
    }

    public abstract void createApple();

    public Image getAppleImage() {
        return appleIco.getImage();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean gotWater() {
        return gotWater;
    }
}
