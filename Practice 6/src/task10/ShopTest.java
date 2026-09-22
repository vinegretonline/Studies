package task10;

import java.util.Scanner;

public class ShopTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop(10);

        shop.add(new Computer(Brand.ASUS, new Processor("Intel Core i5", 6),
                new Memory(16, "DDR4"), new Monitor(24), 72000));
        shop.add(new Computer(Brand.APPLE, new Processor("Apple M3", 8),
                new Memory(8, "LPDDR5"), new Monitor(13.6), 124000));

        System.out.print("Сколько компьютеров добавить? ");
        int n = Integer.parseInt(scanner.nextLine());
        shop.inputData(scanner, n);

        System.out.println("Все компьютеры магазина:");
        shop.printAll();

        System.out.print("Максимальная цена: ");
        int maxPrice = Integer.parseInt(scanner.nextLine());
        System.out.print("Минимальный объём памяти (ГБ): ");
        int minMemory = Integer.parseInt(scanner.nextLine());

        Computer found = shop.findByPrice(maxPrice, minMemory);
        if (found != null) {
            System.out.println("Подходящий компьютер: " + found);
        } else {
            System.out.println("Подходящих компьютеров нет");
        }

        System.out.print("Марка компьютера для удаления: ");
        shop.remove(Brand.valueOf(scanner.nextLine().toUpperCase()));

        System.out.println("Осталось в магазине:");
        shop.printAll();

        scanner.close();
    }
}
