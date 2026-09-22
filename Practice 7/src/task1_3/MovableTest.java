package task1_3;

public class MovableTest {

    public static void main(String[] args) {
        MovableRectangle rectangle = new MovableRectangle(0, 0, 10, 6, 2, 3);

        System.out.println(rectangle);
        System.out.println("Скорости точек совпадают? " + rectangle.speedTest());

        rectangle.moveRight();
        System.out.println("После moveRight: " + rectangle);

        rectangle.moveDown();
        System.out.println("После moveDown: " + rectangle);

        rectangle.moveUp();
        rectangle.moveLeft();
        System.out.println("После moveUp и moveLeft: " + rectangle);

        MovablePoint fast = new MovablePoint(0, 0, 9, 9);
        MovablePoint slow = new MovablePoint(5, 5, 1, 1);
        MovableRectangle broken = new MovableRectangle(fast, slow);

        System.out.println(broken);
        System.out.println("Скорости точек совпадают? " + broken.speedTest());
        broken.moveRight();

        Movable[] objects = {rectangle, fast, slow};
        System.out.println("Двигаем все объекты вниз:");
        for (int i = 0; i < objects.length; i++) {
            objects[i].moveDown();
            System.out.println("  " + objects[i]);
        }
    }
}
