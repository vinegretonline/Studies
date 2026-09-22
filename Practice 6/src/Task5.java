public class Task5 {

    interface Movable {

        void moveUp();

        void moveDown();

        void moveLeft();

        void moveRight();
    }

    static class Point {

        protected double x;
        protected double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static class MovablePoint extends Point implements Movable {

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

    static class Circle {

        protected Point center;
        protected double radius;

        public Circle(Point center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public String toString() {
            return "Окружность с центром " + center + " и радиусом " + radius;
        }
    }

    static class MovableCircle extends Circle implements Movable {

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

    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(5.0, 5.0, 1.5, 2.0);
        System.out.println("Точка: " + point);
        point.moveUp();
        point.moveRight();
        System.out.println("После moveUp и moveRight: " + point);

        MovableCircle circle = new MovableCircle(0.0, 0.0, 3.0, 3.0, 10.0);
        System.out.println(circle);
        System.out.printf("Площадь круга: %.3f%n", circle.getArea());
        circle.moveDown();
        System.out.println("После moveDown: " + circle);

        Movable[] objects = {point, circle};
        for (int i = 0; i < objects.length; i++) {
            objects[i].moveLeft();
            System.out.println("После moveLeft: " + objects[i]);
        }
    }
}
