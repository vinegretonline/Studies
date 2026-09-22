package task5;

public class MovablePoint extends Point implements Movable {

    protected double xSpeed;
    protected double ySpeed;

    public MovablePoint(double x, double y, double xSpeed, double ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
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
        return super.toString() + ", скорость (" + xSpeed + ", " + ySpeed + ")";
    }
}
