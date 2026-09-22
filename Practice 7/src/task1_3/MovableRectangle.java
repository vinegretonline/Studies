package task1_3;

public class MovableRectangle implements Movable {

    private MovablePoint topLeft;
    private MovablePoint bottomRight;

    public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed) {
        topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
        bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
    }

    public MovableRectangle(MovablePoint topLeft, MovablePoint bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public MovablePoint getTopLeft() {
        return topLeft;
    }

    public MovablePoint getBottomRight() {
        return bottomRight;
    }

    public boolean speedTest() {
        return topLeft.getXSpeed() == bottomRight.getXSpeed()
                && topLeft.getYSpeed() == bottomRight.getYSpeed();
    }

    public int getWidth() {
        return bottomRight.getX() - topLeft.getX();
    }

    public int getHeight() {
        return bottomRight.getY() - topLeft.getY();
    }

    public void moveUp() {
        if (!speedTest()) {
            System.out.println("Скорости точек не совпадают, движение невозможно");
            return;
        }
        topLeft.moveUp();
        bottomRight.moveUp();
    }

    public void moveDown() {
        if (!speedTest()) {
            System.out.println("Скорости точек не совпадают, движение невозможно");
            return;
        }
        topLeft.moveDown();
        bottomRight.moveDown();
    }

    public void moveLeft() {
        if (!speedTest()) {
            System.out.println("Скорости точек не совпадают, движение невозможно");
            return;
        }
        topLeft.moveLeft();
        bottomRight.moveLeft();
    }

    public void moveRight() {
        if (!speedTest()) {
            System.out.println("Скорости точек не совпадают, движение невозможно");
            return;
        }
        topLeft.moveRight();
        bottomRight.moveRight();
    }

    public String toString() {
        return "Прямоугольник: верхняя левая " + topLeft
                + ", нижняя правая " + bottomRight
                + ", размер " + getWidth() + " x " + getHeight();
    }
}
