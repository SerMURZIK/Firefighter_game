package classes.snakeClasses.blockClasses;

import javax.swing.*;
import java.awt.*;

public abstract class Block {
    public final static int BODY_SIZE = 50;
    private int x, y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(getIco().getImage(), x, y, null);
    }

    public abstract ImageIcon getIco();

    public abstract void move();

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
}
