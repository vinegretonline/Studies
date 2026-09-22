public class Task4 {

    public static void main(String[] args) {
        int count = 10;
        double partialSum = 0.0;

        System.out.println("Первые " + count + " чисел гармонического ряда:");
        System.out.println("+-----+----------+--------------+------------------+");
        System.out.printf("| %-3s | %-8s | %-12s | %-16s |%n",
                "N", "Дробь", "Значение", "Частичная сумма");
        System.out.println("+-----+----------+--------------+------------------+");

        for (int n = 1; n <= count; n++) {
            double term = 1.0 / n;
            partialSum += term;

            System.out.printf("| %-3d | %-8s | %12.8f | %16.8f |%n",
                    n, "1/" + n, term, partialSum);
        }

        System.out.println("+-----+----------+--------------+------------------+");
        System.out.printf("Сумма первых %d членов ряда: %.8f%n", count, partialSum);
    }
}
