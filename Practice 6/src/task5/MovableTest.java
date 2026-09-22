package task5;

public class MovableTest {

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
