package task4;

import java.util.Scanner;

public class EvenArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива (натуральное число): ");
        int n = scanner.nextInt();

        while (n <= 0) {
            System.out.print("Число должно быть больше нуля. Повторите ввод: ");
            n = scanner.nextInt();
        }

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (n + 1));
        }

        System.out.print("Первый массив: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        int evenCount = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 == 0) {
                evenCount++;
            }
        }

        if (evenCount == 0) {
            System.out.println("Чётных элементов в массиве нет");
        } else {
            int[] even = new int[evenCount];
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] % 2 == 0) {
                    even[index] = array[i];
                    index++;
                }
            }

            System.out.print("Второй массив (только чётные): ");
            for (int i = 0; i < even.length; i++) {
                System.out.print(even[i] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
