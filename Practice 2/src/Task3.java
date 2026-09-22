public class Task3 {

    static class Point {

        private double x;
        private double y;

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
            return "(" + x + "; " + y + ")";
        }
    }

    static class Circle {

        private Point center;
        private double radius;

        public Circle(double x, double y, double radius) {
            center = new Point(x, y);
            this.radius = radius;
        }

        public Point getCenter() {
            return center;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public String toString() {
            return "Окружность с центром " + center + " и радиусом " + radius;
        }
    }

    static class Tester {

        private Circle[] circles;
        private int count;

        public Tester(int size) {
            circles = new Circle[size];
            count = 0;
        }

        public void add(Circle circle) {
            if (count < circles.length) {
                circles[count] = circle;
                count++;
            } else {
                System.out.println("Массив заполнен, окружность не добавлена");
            }
        }

        public void printAll() {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ") " + circles[i]);
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        Tester tester = new Tester(5);

        tester.add(new Circle(0, 0, 3));
        tester.add(new Circle(2.5, -1, 7.2));
        tester.add(new Circle(-4, 8, 1.5));

        System.out.println("Всего окружностей: " + tester.getCount());
        tester.printAll();
    }
}
