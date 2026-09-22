import java.util.Scanner;

public class Task4 {

    interface Inputable {

        void inputData(Scanner scanner, int count);
    }

    static class Computer {

        private String brand;
        private int ram;
        private int price;

        public Computer(String brand, int ram, int price) {
            this.brand = brand;
            this.ram = ram;
            this.price = price;
        }

        public String getBrand() {
            return brand;
        }

        public int getRam() {
            return ram;
        }

        public int getPrice() {
            return price;
        }

        public String toString() {
            return brand + ", ОЗУ " + ram + " ГБ, цена " + price + " руб.";
        }
    }

    static class Shop implements Inputable {

        private Computer[] computers;
        private int count;

        public Shop(int size) {
            computers = new Computer[size];
            count = 0;
        }

        public void inputData(Scanner scanner, int number) {
            for (int i = 0; i < number; i++) {
                System.out.println("Компьютер " + (i + 1) + ":");
                System.out.print("Марка: ");
                String brand = scanner.nextLine();
                System.out.print("Объём ОЗУ (ГБ): ");
                int ram = Integer.parseInt(scanner.nextLine());
                System.out.print("Цена: ");
                int price = Integer.parseInt(scanner.nextLine());
                add(new Computer(brand, ram, price));
            }
        }

        public void add(Computer computer) {
            if (count < computers.length) {
                computers[count] = computer;
                count++;
            } else {
                System.out.println("В магазине нет места");
            }
        }

        public void remove(String brand) {
            for (int i = 0; i < count; i++) {
                if (computers[i].getBrand().equals(brand)) {
                    for (int j = i; j < count - 1; j++) {
                        computers[j] = computers[j + 1];
                    }
                    computers[count - 1] = null;
                    count--;
                    System.out.println("Компьютер " + brand + " удалён");
                    return;
                }
            }
            System.out.println("Компьютер " + brand + " не найден");
        }

        public Computer find(String brand) {
            for (int i = 0; i < count; i++) {
                if (computers[i].getBrand().equals(brand)) {
                    return computers[i];
                }
            }
            return null;
        }

        public void printAll() {
            if (count == 0) {
                System.out.println("Магазин пуст");
            }
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ") " + computers[i]);
            }
        }
    }

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
