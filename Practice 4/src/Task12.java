public class Task12 {

    abstract static class Shape {

        protected String color;
        protected boolean filled;

        public Shape() {
            color = "green";
            filled = true;
        }

        public Shape(String color, boolean filled) {
            this.color = color;
            this.filled = filled;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isFilled() {
            return filled;
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
        }

        public abstract double getArea();

        public abstract double getPerimeter();

        public String toString() {
            return "Shape[color=" + color + ", filled=" + filled + "]";
        }
    }

    static class Circle extends Shape {

        protected double radius;

        public Circle() {
            radius = 1.0;
        }

        public Circle(double radius) {
            this.radius = radius;
        }

        public Circle(double radius, String color, boolean filled) {
            super(color, filled);
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

        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        public String toString() {
            return "Circle[" + super.toString() + ", radius=" + radius + "]";
        }
    }

    static class Rectangle extends Shape {

        protected double width;
        protected double length;

        public Rectangle() {
            width = 1.0;
            length = 1.0;
        }

        public Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        public Rectangle(double width, double length, String color, boolean filled) {
            super(color, filled);
            this.width = width;
            this.length = length;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public double getArea() {
            return width * length;
        }

        public double getPerimeter() {
            return 2 * (width + length);
        }

        public String toString() {
            return "Rectangle[" + super.toString() + ", width=" + width + ", length=" + length + "]";
        }
    }

    static class Square extends Rectangle {

        public Square() {
            super();
        }

        public Square(double side) {
            super(side, side);
        }

        public Square(double side, String color, boolean filled) {
            super(side, side, color, filled);
        }

        public double getSide() {
            return width;
        }

        public void setSide(double side) {
            width = side;
            length = side;
        }

        public void setWidth(double side) {
            setSide(side);
        }

        public void setLength(double side) {
            setSide(side);
        }

        public String toString() {
            return "Square[" + super.toString() + "]";
        }
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5.5, "RED", false),
                new Rectangle(1.0, 2.0, "BLUE", true),
                new Square(4.0, "GREEN", false)
        };

        for (int i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];
            System.out.println(shape);
            System.out.printf("  площадь: %.3f%n", shape.getArea());
            System.out.printf("  периметр: %.3f%n", shape.getPerimeter());
            System.out.println("  цвет: " + shape.getColor() + ", закрашена: " + shape.isFilled());
        }

        Square square = new Square(3.0);
        square.setWidth(7.0);
        System.out.println("Квадрат после setWidth(7.0): " + square);
    }
}
