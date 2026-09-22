public class Task1 {

    public static void main(String[] args) {
        int[] numbers = {12, -5, 34, 7, 0, 25, -18, 9, 41, 3};

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("numbers[%d] = %d%n", i, numbers[i]);
            sum += numbers[i];
        }

        double average = (double) sum / numbers.length;

        System.out.println("---------------------------------");
        System.out.println("Количество элементов: " + numbers.length);
        System.out.println("Сумма элементов: " + sum);
        System.out.printf("Среднее арифметическое: %.3f%n", average);
    }
}
