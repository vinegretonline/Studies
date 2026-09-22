package task2;

public class TestBall {

    public static void main(String[] args) {
        Ball b1 = new Ball(2.5, 4.0);
        Ball b2 = new Ball();

        System.out.println("Первый мяч: " + b1);
        System.out.println("Второй мяч: " + b2);

        b1.move(1.5, -2.0);
        System.out.println("После перемещения: " + b1);

        b2.setXY(10.0, 20.0);
        System.out.println("После setXY: " + b2);

        b2.setX(15.0);
        b2.setY(25.0);
        System.out.println("x = " + b2.getX() + ", y = " + b2.getY());
    }
}
