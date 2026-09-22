package task10;

import java.util.Scanner;

public class Shop implements Inputable {

    private Computer[] computers;
    private int count;

    public Shop(int size) {
        computers = new Computer[size];
        count = 0;
    }

    public void inputData(Scanner scanner, int number) {
        for (int i = 0; i < number; i++) {
            System.out.println("Компьютер " + (i + 1) + ":");

            System.out.print("Марка (ASUS, LENOVO, ACER, HP, APPLE, DELL): ");
            Brand brand = Brand.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Модель процессора: ");
            String cpuModel = scanner.nextLine();
            System.out.print("Количество ядер: ");
            int cores = Integer.parseInt(scanner.nextLine());

            System.out.print("Объём памяти (ГБ): ");
            int memorySize = Integer.parseInt(scanner.nextLine());
            System.out.print("Тип памяти: ");
            String memoryType = scanner.nextLine();

            System.out.print("Диагональ монитора: ");
            double diagonal = Double.parseDouble(scanner.nextLine());

            System.out.print("Цена: ");
            int price = Integer.parseInt(scanner.nextLine());

            add(new Computer(brand, new Processor(cpuModel, cores),
                    new Memory(memorySize, memoryType), new Monitor(diagonal), price));
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

    public void remove(Brand brand) {
        for (int i = 0; i < count; i++) {
            if (computers[i].getBrand() == brand) {
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

    public Computer findByPrice(int maxPrice, int minMemory) {
        for (int i = 0; i < count; i++) {
            if (computers[i].getPrice() <= maxPrice && computers[i].getMemory().getSize() >= minMemory) {
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
