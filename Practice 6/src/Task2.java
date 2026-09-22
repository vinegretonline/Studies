public class Task2 {

    interface Movable {

        void moveUp();

        void moveDown();

        void moveLeft();

        void moveRight();
    }

    static class MovablePoint implements Movable {

        int x;
        int y;
        int xSpeed;
        int ySpeed;

        public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
            this.x = x;
            this.y = y;
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed;
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

    public static void main(String[] args) {
        MovableRectangle rectangle = new MovableRectangle(0, 0, 10, 8, 4, 4);
        System.out.println(rectangle);
        System.out.println("Скорости точек совпадают? " + rectangle.speedTest());

        rectangle.moveRight();
        System.out.println("После moveRight: " + rectangle);

        rectangle.moveDown();
        System.out.println("После moveDown: " + rectangle);

        MovableRectangle broken = new MovableRectangle(
                new MovablePoint(0, 0, 9, 9), new MovablePoint(5, 5, 1, 1));
        System.out.println(broken);
        System.out.println("Скорости точек совпадают? " + broken.speedTest());
        broken.moveLeft();
    }
}
