package task41_8;

public class ShapeTest {

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
