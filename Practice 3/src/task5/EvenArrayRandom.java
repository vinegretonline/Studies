package task5;

import java.util.Random;
import java.util.Scanner;

public class EvenArrayRandom {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размер массива (натуральное число): ");
        int n = scanner.nextInt();

        while (n <= 0) {
            System.out.print("Число должно быть больше нуля. Повторите ввод: ");
            n = scanner.nextInt();
        }

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n + 1);
        }

        System.out.print("Первый массив: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        int[] even = new int[n];
        int evenCount = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 == 0) {
                even[evenCount] = array[i];
                evenCount++;
            }
        }

        if (evenCount == 0) {
            System.out.println("Чётных элементов в массиве нет");
        } else {
            System.out.print("Второй массив (только чётные): ");
            for (int i = 0; i < evenCount; i++) {
                System.out.print(even[i] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
