package classes.apples;

import classes.snakeClasses.blockClasses.Block;

import javax.swing.*;

public class RedApple extends Apple {

    public RedApple(int windowWidth, int windowHeight, ImageIcon appleIco) {
        super(windowWidth, windowHeight, appleIco);
        createApple();
    }

    @Override
    public void createApple() {
        setX(((int) (Math.random() * windowWidth / Block.BODY_SIZE)) * Block.BODY_SIZE);
        setY(((int) (Math.random() * (windowHeight - Block.BODY_SIZE) / Block.BODY_SIZE)) * Block.BODY_SIZE + Block.BODY_SIZE);
    }
}
