public class Task1 {

    interface Movable {

        void moveUp();

        void moveDown();

        void moveLeft();

        void moveRight();
    }

    static class MovableRectangle implements Movable {

        private int x;
        private int y;
        private int width;
        private int height;
        private int speed;

        public MovableRectangle(int x, int y, int width, int height, int speed) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.speed = speed;
        }

        public void moveUp() {
            y = y - speed;
        }

        public void moveDown() {
            y = y + speed;
        }

        public void moveLeft() {
            x = x - speed;
        }

        public void moveRight() {
            x = x + speed;
        }

        public String toString() {
            return "Прямоугольник в точке (" + x + ", " + y + "), размер " + width + " x " + height;
        }
    }

    public static void main(String[] args) {
        MovableRectangle rectangle = new MovableRectangle(0, 0, 10, 6, 2);

        System.out.println(rectangle);

        rectangle.moveRight();
        System.out.println("После moveRight: " + rectangle);

        rectangle.moveDown();
        System.out.println("После moveDown: " + rectangle);

        rectangle.moveUp();
        rectangle.moveLeft();
        System.out.println("После moveUp и moveLeft: " + rectangle);
    }
}
