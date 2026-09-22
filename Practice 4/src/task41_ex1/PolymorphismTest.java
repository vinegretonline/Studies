package task41_ex1;

import task41_8.Circle;
import task41_8.Rectangle;
import task41_8.Shape;

// Упражнение 1. Разбор ошибок листинга 4.1.3 и рабочая версия кода.
//
// Ошибки исходного листинга:
// 1) В листинге использованы «кавычки-ёлочки» вместо обычных двойных кавычек -
//    такой литерал не компилируется.
// 2) s1.getRadius() - ошибка. Тип ссылки Shape, а getRadius() объявлен только
//    в Circle. Компилятор смотрит на тип ссылки, а не на тип объекта.
// 3) Shape s2 = new Shape(); - ошибка. Shape объявлен абстрактным, создать
//    его экземпляр нельзя.
// 4) s3.getLength() - ошибка по той же причине, что и пункт 2:
//    getLength() есть только в Rectangle.
//
// Методы getArea(), getPerimeter() и toString() вызываются без ошибок и
// выполняются в версии подкласса - это и есть полиморфизм: версия метода
// выбирается по фактическому типу объекта во время выполнения.

public class PolymorphismTest {

    public static void main(String[] args) {
        Shape s1 = new Circle(5.5, "RED", false);

        System.out.println(s1);
        System.out.println(s1.getArea());
        System.out.println(s1.getPerimeter());
        System.out.println(s1.getColor());
        System.out.println(s1.isFilled());
        // System.out.println(s1.getRadius()); - ошибка компиляции

        Circle c1 = (Circle) s1;
        System.out.println(c1);
        System.out.println(c1.getArea());
        System.out.println(c1.getPerimeter());
        System.out.println(c1.getColor());
        System.out.println(c1.isFilled());
        System.out.println(c1.getRadius());

        // Shape s2 = new Shape(); - ошибка компиляции, класс абстрактный

        Shape s3 = new Rectangle(1.0, 2.0, "RED", false);
        System.out.println(s3);
        System.out.println(s3.getArea());
        System.out.println(s3.getPerimeter());
        System.out.println(s3.getColor());
        // System.out.println(s3.getLength()); - ошибка компиляции

        Rectangle r1 = (Rectangle) s3;
        System.out.println(r1.getLength());
        System.out.println(r1.getWidth());
    }
}
