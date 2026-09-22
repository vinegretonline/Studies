package task41_ex1;

import task41_8.Circle;
import task41_8.Rectangle;
import task41_8.Shape;

// Exercise 1. The lines of listing 4.1.3 that do not compile are commented out and
// the reason is written next to them. In short, the compiler looks at the type of the
// reference and not at the type of the object, so through a Shape reference you can
// only use what is declared in Shape. At the same time getArea and getPerimeter run
// the version from the subclass, and this is polymorphism.

public class PolymorphismTest {

    public static void main(String[] args) {
        Shape s1 = new Circle(5.5, "RED", false);

        System.out.println(s1);
        System.out.println(s1.getArea());
        System.out.println(s1.getPerimeter());
        System.out.println(s1.getColor());
        System.out.println(s1.isFilled());
        // System.out.println(s1.getRadius()); - does not compile

        Circle c1 = (Circle) s1;
        System.out.println(c1);
        System.out.println(c1.getArea());
        System.out.println(c1.getPerimeter());
        System.out.println(c1.getColor());
        System.out.println(c1.isFilled());
        System.out.println(c1.getRadius());

        // Shape s2 = new Shape(); - does not compile, the class is abstract

        Shape s3 = new Rectangle(1.0, 2.0, "RED", false);
        System.out.println(s3);
        System.out.println(s3.getArea());
        System.out.println(s3.getPerimeter());
        System.out.println(s3.getColor());
        // System.out.println(s3.getLength()); - does not compile

        Rectangle r1 = (Rectangle) s3;
        System.out.println(r1.getLength());
        System.out.println(r1.getWidth());
    }
}
