public class Task2 {

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
            return "(" + x + ", " + y + ")";
        }
    }

    static class MovableRectangle implements Movable {

        private MovablePoint topLeft;
        private MovablePoint bottomRight;

        public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed) {
            topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
            bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
        }

        public MovablePoint getTopLeft() {
            return topLeft;
        }

        public MovablePoint getBottomRight() {
            return bottomRight;
        }

        public void moveUp() {
            topLeft.moveUp();
            bottomRight.moveUp();
        }

        public void moveDown() {
            topLeft.moveDown();
            bottomRight.moveDown();
        }

        public void moveLeft() {
            topLeft.moveLeft();
            bottomRight.moveLeft();
        }

        public void moveRight() {
            topLeft.moveRight();
            bottomRight.moveRight();
        }

        public String toString() {
            return "Прямоугольник: верхняя левая " + topLeft + ", нижняя правая " + bottomRight;
        }
    }

    public static void main(String[] args) {
        MovableRectangle rectangle = new MovableRectangle(0, 0, 10, 6, 2, 3);

        System.out.println(rectangle);

        rectangle.moveRight();
        System.out.println("После moveRight: " + rectangle);

        rectangle.moveDown();
        System.out.println("После moveDown: " + rectangle);

        Movable[] objects = {rectangle, rectangle.getTopLeft()};
        for (int i = 0; i < objects.length; i++) {
            objects[i].moveLeft();
            System.out.println("После moveLeft: " + objects[i]);
        }
    }
}
