public class Task3 {

    interface Movable {

        void moveUp();

        void moveDown();

        void moveLeft();

        void moveRight();
    }

    static class MovablePoint implements Movable {

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

    static class MovableRectangle implements Movable {

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

    public static void main(String[] args) {
        MovableRectangle rectangle = new MovableRectangle(0, 0, 10, 6, 2, 3);

        System.out.println(rectangle);
        System.out.println("Скорости точек совпадают? " + rectangle.speedTest());

        rectangle.moveRight();
        System.out.println("После moveRight: " + rectangle);

        rectangle.moveDown();
        System.out.println("После moveDown: " + rectangle);

        MovablePoint fast = new MovablePoint(0, 0, 9, 9);
        MovablePoint slow = new MovablePoint(5, 5, 1, 1);
        MovableRectangle broken = new MovableRectangle(fast, slow);

        System.out.println(broken);
        System.out.println("Скорости точек совпадают? " + broken.speedTest());
        broken.moveRight();
    }
}
