import java.util.Scanner;

public class Task7 {

    static class CurrencyConverter {

        private double usdRate;
        private double eurRate;
        private double cnyRate;

        public CurrencyConverter(double usdRate, double eurRate, double cnyRate) {
            this.usdRate = usdRate;
            this.eurRate = eurRate;
            this.cnyRate = cnyRate;
        }

        public double toUsd(double rubles) {
            return rubles / usdRate;
        }

        public double toEur(double rubles) {
            return rubles / eurRate;
        }

        public double toCny(double rubles) {
            return rubles / cnyRate;
        }

        public void printTable(double rubles) {
            System.out.println("+------------+--------------+");
            System.out.printf("| %-10s | %12s |%n", "Валюта", "Сумма");
            System.out.println("+------------+--------------+");
            System.out.printf("| %-10s | %12.2f |%n", "Рубли", rubles);
            System.out.printf("| %-10s | %12.2f |%n", "Доллары", toUsd(rubles));
            System.out.printf("| %-10s | %12.2f |%n", "Евро", toEur(rubles));
            System.out.printf("| %-10s | %12.2f |%n", "Юани", toCny(rubles));
            System.out.println("+------------+--------------+");
        }
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter(81.50, 95.20, 11.40);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сумму в рублях: ");
        double rubles = scanner.nextDouble();

        converter.printTable(rubles);

        scanner.close();
    }
}
