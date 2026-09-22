// Задание 4. Ввод массива с клавиатуры, сумма циклами do while и while,
// поиск максимального и минимального элементов

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов массива: ");
        int n = scanner.nextInt();

        while (n <= 0) {
            System.out.print("Размер должен быть больше нуля. Повторите ввод: ");
            n = scanner.nextInt();
        }

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("array[" + i + "] = ");
            array[i] = scanner.nextInt();
        }

        int sumDoWhile = 0;
        int i = 0;
        do {
            sumDoWhile += array[i];
            i++;
        } while (i < array.length);

        int sumWhile = 0;
        int j = 0;
        while (j < array.length) {
            sumWhile += array[j];
            j++;
        }

        int max = array[0];
        int min = array[0];
        int maxIndex = 0;
        int minIndex = 0;

        int k = 1;
        while (k < array.length) {
            if (array[k] > max) {
                max = array[k];
                maxIndex = k;
            }
            if (array[k] < min) {
                min = array[k];
                minIndex = k;
            }
            k++;
        }

        System.out.println("---------------------------------");
        System.out.print("Введённый массив: ");
        for (int idx = 0; idx < array.length; idx++) {
            System.out.print(array[idx] + (idx < array.length - 1 ? ", " : ""));
        }
        System.out.println();

        System.out.println("Сумма (do while): " + sumDoWhile);
        System.out.println("Сумма (while): " + sumWhile);
        System.out.println("Максимальный элемент: " + max + " (индекс " + maxIndex + ")");
        System.out.println("Минимальный элемент: " + min + " (индекс " + minIndex + ")");

        scanner.close();
    }
}
