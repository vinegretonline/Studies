package task1_3;

public class MovablePoint implements Movable {

    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void moveUp() {
        y = y - ySpeed;
    }

    public void moveDown() {
        y = y + ySpeed;
    }

    public void moveLeft() {
        x = x - xSpeed;
    }

    public void moveRight() {
        x = x + xSpeed;
    }

    public String toString() {
        return "(" + x + ", " + y + "), скорость (" + xSpeed + ", " + ySpeed + ")";
    }
}
