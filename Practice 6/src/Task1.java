public class Task1 {

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

    static class MovableCircle implements Movable {

        private int radius;
        private MovablePoint center;

        public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
            center = new MovablePoint(x, y, xSpeed, ySpeed);
            this.radius = radius;
        }

        public int getRadius() {
            return radius;
        }

        public void moveUp() {
            center.moveUp();
        }

        public void moveDown() {
            center.moveDown();
        }

        public void moveLeft() {
            center.moveLeft();
        }

        public void moveRight() {
            center.moveRight();
        }

        public String toString() {
            return "Окружность: центр " + center + ", радиус " + radius;
        }
    }

    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(10, 20, 2, 3);
        System.out.println("Точка: " + point);
        point.moveUp();
        System.out.println("После moveUp: " + point);
        point.moveRight();
        System.out.println("После moveRight: " + point);

        MovableCircle circle = new MovableCircle(0, 0, 5, 5, 12);
        System.out.println(circle);
        circle.moveDown();
        circle.moveLeft();
        System.out.println("После moveDown и moveLeft: " + circle);

        Movable[] objects = {point, circle};
        System.out.println("Двигаем объекты вниз через интерфейсную ссылку:");
        for (int i = 0; i < objects.length; i++) {
            objects[i].moveDown();
            System.out.println("  " + objects[i]);
        }
    }
}
