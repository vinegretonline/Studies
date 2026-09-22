package task6;

public class CircleTest {

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
