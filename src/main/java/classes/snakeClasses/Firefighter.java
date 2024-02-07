package classes.snakeClasses;

import classes.snakeClasses.blockClasses.Body;

import static classes.snakeClasses.blockClasses.Block.BODY_SIZE;

public class Firefighter {
    private short score = 0;
    protected final short startSpeed = 100;
    private short speed = startSpeed;
    private Body body;

    public Firefighter(Direction direction, int initialX, int initialY) {
        createSnake(initialX, initialY, direction);
    }

    public void createSnake(int initialX, int initialY, Direction direction) {
        body = new Body(initialX, initialY);
        setDirection(direction);
    }

    public Body getBody() {
        return body;
    }

    public void checkDeath(int windowWidth, int windowHeight) {
        if (getDirection().equals(Direction.UP) && body.getY() < 0) {
            body.setY(windowHeight - BODY_SIZE);
        }
        if (getDirection().equals(Direction.DOWN) && body.getY() > (windowHeight - BODY_SIZE)) {
            body.setY(BODY_SIZE);
        }
        if (getDirection().equals(Direction.LEFT) && body.getX() < 0) {
            body.setX(windowWidth - BODY_SIZE);
        }
        if (getDirection().equals(Direction.RIGHT) && body.getX() > (windowWidth - BODY_SIZE)) {
            body.setX(0);
        }
    }

    public Direction getDirection() {
        return getBody().getDirection();
    }

    public void setDirection(Direction direction) {
        getBody().setDirection(direction);
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public short getSpeed() {
        return speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public Firefighter getSnake() {
        return this;
    }

    public void setWater(boolean water) {
        body.setHaveWater(water);
    }
}
