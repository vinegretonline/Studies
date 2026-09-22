package task1;

import java.util.Random;

public class RandomArray {

    public static void printArray(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%.3f ", array[i]);
        }
        System.out.println();
    }

    public static void sortArray(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        double[] first = new double[10];
        for (int i = 0; i < first.length; i++) {
            first[i] = Math.random() * 100;
        }

        System.out.println("Массив, созданный методом Math.random():");
        printArray(first);
        sortArray(first);
        System.out.println("После сортировки:");
        printArray(first);

        Random random = new Random();
        double[] second = new double[10];
        for (int i = 0; i < second.length; i++) {
            second[i] = random.nextDouble() * 100;
        }

        System.out.println("Массив, созданный классом Random:");
        printArray(second);
        sortArray(second);
        System.out.println("После сортировки:");
        printArray(second);
    }
}
