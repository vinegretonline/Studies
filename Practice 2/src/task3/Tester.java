package task3;

public class Tester {

    private Circle[] circles;
    private int count;

    public Tester(int size) {
        circles = new Circle[size];
        count = 0;
    }

    public void add(Circle circle) {
        if (count < circles.length) {
            circles[count] = circle;
            count++;
        } else {
            System.out.println("Массив заполнен, окружность не добавлена");
        }
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ") " + circles[i]);
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Tester tester = new Tester(5);

        tester.add(new Circle(0, 0, 3));
        tester.add(new Circle(2.5, -1, 7.2));
        tester.add(new Circle(-4, 8, 1.5));

        System.out.println("Всего окружностей: " + tester.getCount());
        tester.printAll();
    }
}
