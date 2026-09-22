package task1_2;

public class MovableTest {

    public static void main(String[] args) {
        MovablePoint point = new MovablePoint(10, 20, 2, 3);
        System.out.println("Точка: " + point);
        point.moveUp();
        System.out.println("После moveUp: " + point);
        point.moveRight();
        System.out.println("После moveRight: " + point);

        MovableCircle circle = new MovableCircle(0, 0, 5, 5, 12);
        System.out.println(circle);
        circle.moveDown();
        circle.moveLeft();
        System.out.println("После moveDown и moveLeft: " + circle);

        MovableRectangle rectangle = new MovableRectangle(0, 0, 10, 8, 4, 4);
        System.out.println(rectangle);
        System.out.println("Скорости точек совпадают? " + rectangle.speedTest());
        rectangle.moveRight();
        System.out.println("После moveRight: " + rectangle);

        Movable[] objects = {point, circle, rectangle};
        System.out.println("Двигаем все объекты вниз через интерфейсную ссылку:");
        for (int i = 0; i < objects.length; i++) {
            objects[i].moveDown();
            System.out.println("  " + objects[i]);
        }
    }
}
