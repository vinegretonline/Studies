package task1_2;

public class MovableRectangle implements Movable {

    private MovablePoint topLeft;
    private MovablePoint bottomRight;

    public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed) {
        topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
        bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
    }

    public boolean speedTest() {
        return topLeft.xSpeed == bottomRight.xSpeed && topLeft.ySpeed == bottomRight.ySpeed;
    }

    public void moveUp() {
        if (speedTest()) {
            topLeft.moveUp();
            bottomRight.moveUp();
        } else {
            System.out.println("У точек разная скорость, двигать прямоугольник нельзя");
        }
    }

    public void moveDown() {
        if (speedTest()) {
            topLeft.moveDown();
            bottomRight.moveDown();
        } else {
            System.out.println("У точек разная скорость, двигать прямоугольник нельзя");
        }
    }

    public void moveLeft() {
        if (speedTest()) {
            topLeft.moveLeft();
            bottomRight.moveLeft();
        } else {
            System.out.println("У точек разная скорость, двигать прямоугольник нельзя");
        }
    }

    public void moveRight() {
        if (speedTest()) {
            topLeft.moveRight();
            bottomRight.moveRight();
        } else {
            System.out.println("У точек разная скорость, двигать прямоугольник нельзя");
        }
    }

    public String toString() {
        return "Прямоугольник: верхняя левая " + topLeft + ", нижняя правая " + bottomRight;
    }
}
