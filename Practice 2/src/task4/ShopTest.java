package task4;

import java.util.Scanner;

public class ShopTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Сколько компьютеров добавить в магазин? ");
        int n = Integer.parseInt(scanner.nextLine());

        Shop shop = new Shop(n);
        shop.inputData(scanner, n);

        System.out.println("Компьютеры в магазине:");
        shop.printAll();

        System.out.print("Какой компьютер найти? Введите марку: ");
        String brand = scanner.nextLine();

        Computer found = shop.find(brand);
        if (found != null) {
            System.out.println("Найден: " + found);
        } else {
            System.out.println("Такого компьютера нет");
        }

        System.out.print("Какой компьютер удалить? Введите марку: ");
        shop.remove(scanner.nextLine());

        System.out.println("Осталось в магазине:");
        shop.printAll();
    }
}
