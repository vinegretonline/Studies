import java.util.Scanner;

public class Task8 {

    static class Product {

        private String name;
        private double priceRub;

        public Product(String name, double priceRub) {
            this.name = name;
            this.priceRub = priceRub;
        }

        public String getName() {
            return name;
        }

        public double getPriceRub() {
            return priceRub;
        }
    }

    public static void main(String[] args) {
        Product[] products = {
                new Product("Наушники", 2990),
                new Product("Клавиатура", 4500),
                new Product("Монитор", 18700),
                new Product("Мышь", 1250)
        };

        String[] currencies = {"рубли", "доллары", "евро", "юани"};
        double[] rates = {1.0, 81.50, 95.20, 11.40};
        String[] signs = {"руб.", "$", "EUR", "CNY"};

        Scanner scanner = new Scanner(System.in);

        System.out.println("Товары в магазине:");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("%d) %-12s %10.2f руб.%n", i + 1, products[i].getName(), products[i].getPriceRub());
        }

        System.out.print("Выберите номер товара: ");
        int choice = scanner.nextInt();

        while (choice < 1 || choice > products.length) {
            System.out.print("Нет такого товара. Повторите ввод: ");
            choice = scanner.nextInt();
        }

        System.out.print("Введите количество: ");
        int amount = scanner.nextInt();

        while (amount <= 0) {
            System.out.print("Количество должно быть больше нуля. Повторите ввод: ");
            amount = scanner.nextInt();
        }

        System.out.println("Валюта оплаты:");
        for (int i = 0; i < currencies.length; i++) {
            System.out.println((i + 1) + ") " + currencies[i]);
        }

        System.out.print("Выберите номер валюты: ");
        int currency = scanner.nextInt();

        while (currency < 1 || currency > currencies.length) {
            System.out.print("Нет такой валюты. Повторите ввод: ");
            currency = scanner.nextInt();
        }

        Product product = products[choice - 1];
        double totalRub = product.getPriceRub() * amount;
        double total = totalRub / rates[currency - 1];

        System.out.println("+----------------------+--------------+");
        System.out.printf("| %-20s | %12s |%n", "Товар", product.getName());
        System.out.printf("| %-20s | %12d |%n", "Количество", amount);
        System.out.printf("| %-20s | %12.2f |%n", "Цена за штуку, руб.", product.getPriceRub());
        System.out.printf("| %-20s | %12.2f |%n", "Итого, руб.", totalRub);
        System.out.printf("| %-20s | %9.2f %s |%n", "К оплате", total, signs[currency - 1]);
        System.out.println("+----------------------+--------------+");

        scanner.close();
    }
}
