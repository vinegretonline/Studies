package task2;

import java.util.Random;

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
        }
    }

    public Circle getSmallest() {
        Circle smallest = circles[0];
        for (int i = 1; i < count; i++) {
            if (circles[i].getRadius() < smallest.getRadius()) {
                smallest = circles[i];
            }
        }
        return smallest;
    }

    public Circle getBiggest() {
        Circle biggest = circles[0];
        for (int i = 1; i < count; i++) {
            if (circles[i].getRadius() > biggest.getRadius()) {
                biggest = circles[i];
            }
        }
        return biggest;
    }

    public void sortByRadius() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (circles[j].getRadius() > circles[j + 1].getRadius()) {
                    Circle temp = circles[j];
                    circles[j] = circles[j + 1];
                    circles[j + 1] = temp;
                }
            }
        }
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ") " + circles[i]);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Tester tester = new Tester(6);

        for (int i = 0; i < 6; i++) {
            double x = random.nextDouble() * 20 - 10;
            double y = random.nextDouble() * 20 - 10;
            double radius = 1 + random.nextDouble() * 9;
            tester.add(new Circle(x, y, radius));
        }

        System.out.println("Окружности:");
        tester.printAll();

        System.out.println("Самая маленькая: " + tester.getSmallest());
        System.out.println("Самая большая: " + tester.getBiggest());

        tester.sortByRadius();
        System.out.println("После сортировки по радиусу:");
        tester.printAll();
    }
}
