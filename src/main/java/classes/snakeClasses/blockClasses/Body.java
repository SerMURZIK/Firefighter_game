package classes.snakeClasses.blockClasses;

import classes.snakeClasses.Direction;

import javax.swing.*;

public class Body extends Block {
    private Direction direction = Direction.LEFT;
    private boolean haveWater = false;

    public Body(int x, int y) {
        super(x, y);
    }


    @Override
    public ImageIcon getIco() {
        return switch (direction) {
            case UP, LEFT -> getHeadLeft();
            case DOWN, RIGHT -> getHeadRight();
        };
    }

    @Override
    public void move() {
        switch (direction) {
            case UP:
                setY(getY() - Body.BODY_SIZE / 10);
                break;
            case DOWN:
                setY(getY() + Body.BODY_SIZE / 10);
                break;
            case LEFT:
                setX(getX() - Body.BODY_SIZE / 10);
                break;
            case RIGHT:
                setX(getX() + Body.BODY_SIZE / 10);
                break;
        }
    }

    public ImageIcon getHeadRight() {
        return new ImageIcon(haveWater ?
                "src/main/resources/files/sprites/ff/withWater/ffwRight.png" :
                "src/main/resources/files/sprites/ff/withoutWater/ffRight.png");
    }

    public ImageIcon getHeadLeft() {
        return new ImageIcon(haveWater ?
                "src/main/resources/files/sprites/ff/withWater/ffwLeft.png" :
                "src/main/resources/files/sprites/ff/withoutWater/ffLeft.png");
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setHaveWater(boolean haveWater) {
        this.haveWater = haveWater;
    }
}
