package task5;

public class MovableCircle extends Circle implements Movable {

    public MovableCircle(double x, double y, double xSpeed, double ySpeed, double radius) {
        super(new MovablePoint(x, y, xSpeed, ySpeed), radius);
    }

    private MovablePoint getMovableCenter() {
        return (MovablePoint) center;
    }

    public void moveUp() {
        getMovableCenter().moveUp();
    }

    public void moveDown() {
        getMovableCenter().moveDown();
    }

    public void moveLeft() {
        getMovableCenter().moveLeft();
    }

    public void moveRight() {
        getMovableCenter().moveRight();
    }

    public String toString() {
        return "Движущаяся " + super.toString().toLowerCase();
    }
}
