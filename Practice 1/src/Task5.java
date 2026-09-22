import java.util.Scanner;

public class Task5 {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не определён: " + n);
        }
        if (n > 20) {
            throw new IllegalArgumentException("Переполнение типа long: " + n + "! слишком велик");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] testValues = {0, 1, 2, 5, 10, 15, 20};

        System.out.println("Проверка работы метода factorial():");
        for (int i = 0; i < testValues.length; i++) {
            System.out.printf("%2d! = %d%n", testValues[i], factorial(testValues[i]));
        }

        System.out.println("---------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число от 0 до 20 для расчёта факториала: ");

        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            try {
                System.out.printf("%d! = %d%n", n, factorial(n));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } else {
            System.out.println("Введено не целое число.");
        }

        scanner.close();
    }
}
