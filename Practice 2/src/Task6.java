public class Task6 {

    static class Circle {

        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public double getLength() {
            return 2 * Math.PI * radius;
        }

        public boolean isEqual(Circle other) {
            return radius == other.getRadius();
        }

        public String toString() {
            return "Окружность с радиусом " + radius;
        }
    }

    public static void main(String[] args) {
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(5);
        Circle c3 = new Circle(8.5);

        System.out.println(c1);
        System.out.printf("Площадь круга: %.3f%n", c1.getArea());
        System.out.printf("Длина окружности: %.3f%n", c1.getLength());

        System.out.println(c3);
        System.out.printf("Площадь круга: %.3f%n", c3.getArea());
        System.out.printf("Длина окружности: %.3f%n", c3.getLength());

        System.out.println("c1 и c2 равны? " + c1.isEqual(c2));
        System.out.println("c1 и c3 равны? " + c1.isEqual(c3));

        c1.setRadius(8.5);
        System.out.println("После изменения радиуса c1 и c3 равны? " + c1.isEqual(c3));
    }
}
