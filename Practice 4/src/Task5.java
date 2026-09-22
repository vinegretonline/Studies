public class Task5 {

    static class Shape {

        public String getType() {
            return "Фигура";
        }

        public double getArea() {
            return 0;
        }

        public double getPerimeter() {
            return 0;
        }

        public String toString() {
            return getType();
        }
    }

    static class Circle extends Shape {

        protected double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public String getType() {
            return "Круг";
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }

        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        public String toString() {
            return getType() + " с радиусом " + radius;
        }
    }

    static class Rectangle extends Shape {

        protected double width;
        protected double length;

        public Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        public double getWidth() {
            return width;
        }

        public double getLength() {
            return length;
        }

        public String getType() {
            return "Прямоугольник";
        }

        public double getArea() {
            return width * length;
        }

        public double getPerimeter() {
            return 2 * (width + length);
        }

        public String toString() {
            return getType() + " " + width + " x " + length;
        }
    }

    static class Square extends Rectangle {

        public Square(double side) {
            super(side, side);
        }

        public double getSide() {
            return width;
        }

        public String getType() {
            return "Квадрат";
        }

        public String toString() {
            return getType() + " со стороной " + getSide();
        }
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 7),
                new Square(3),
                new Shape()
        };

        for (int i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];
            System.out.println(shape);
            System.out.println("  тип: " + shape.getType());
            System.out.printf("  площадь: %.3f%n", shape.getArea());
            System.out.printf("  периметр: %.3f%n", shape.getPerimeter());
        }
    }
}
