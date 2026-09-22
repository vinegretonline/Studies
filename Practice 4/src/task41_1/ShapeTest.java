package task41_1;

public class ShapeTest {

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
