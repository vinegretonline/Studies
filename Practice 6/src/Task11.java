import java.util.Scanner;

public class Task11 {

    interface Convertable {

        double convert(double celsius);

        String getUnit();
    }

    static class ToKelvin implements Convertable {

        public double convert(double celsius) {
            return celsius + 273.15;
        }

        public String getUnit() {
            return "K";
        }
    }

    static class ToFahrenheit implements Convertable {

        public double convert(double celsius) {
            return celsius * 9 / 5 + 32;
        }

        public String getUnit() {
            return "F";
        }
    }

    public static void main(String[] args) {
        Convertable[] converters = {new ToKelvin(), new ToFahrenheit()};

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите температуру по Цельсию: ");
        double celsius = scanner.nextDouble();

        for (int i = 0; i < converters.length; i++) {
            System.out.printf("%.2f C = %.2f %s%n",
                    celsius, converters[i].convert(celsius), converters[i].getUnit());
        }

        System.out.println();
        System.out.println("Таблица перевода:");
        System.out.println("+----------+----------+----------+");
        System.out.printf("| %8s | %8s | %8s |%n", "C", "K", "F");
        System.out.println("+----------+----------+----------+");
        for (double t = -40; t <= 100; t = t + 20) {
            System.out.printf("| %8.2f | %8.2f | %8.2f |%n",
                    t, converters[0].convert(t), converters[1].convert(t));
        }
        System.out.println("+----------+----------+----------+");

        scanner.close();
    }
}
