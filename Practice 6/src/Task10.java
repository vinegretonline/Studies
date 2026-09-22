import java.util.Scanner;

public class Task10 {

    enum Brand {

        ASUS, LENOVO, ACER, HP, APPLE, DELL
    }

    interface Inputable {

        void inputData(Scanner scanner, int count);
    }

    static class Processor {

        private String model;
        private int cores;

        public Processor(String model, int cores) {
            this.model = model;
            this.cores = cores;
        }

        public String getModel() {
            return model;
        }

        public int getCores() {
            return cores;
        }

        public String toString() {
            return model + " (" + cores + " ядер)";
        }
    }

    static class Memory {

        private int size;
        private String type;

        public Memory(int size, String type) {
            this.size = size;
            this.type = type;
        }

        public int getSize() {
            return size;
        }

        public String toString() {
            return size + " ГБ " + type;
        }
    }

    static class Monitor {

        private double diagonal;

        public Monitor(double diagonal) {
            this.diagonal = diagonal;
        }

        public double getDiagonal() {
            return diagonal;
        }

        public String toString() {
            return diagonal + " дюймов";
        }
    }

    static class Computer {

        private Brand brand;
        private Processor processor;
        private Memory memory;
        private Monitor monitor;
        private int price;

        public Computer(Brand brand, Processor processor, Memory memory, Monitor monitor, int price) {
            this.brand = brand;
            this.processor = processor;
            this.memory = memory;
            this.monitor = monitor;
            this.price = price;
        }

        public Brand getBrand() {
            return brand;
        }

        public Memory getMemory() {
            return memory;
        }

        public int getPrice() {
            return price;
        }

        public String toString() {
            return String.format("%-8s %-22s %-14s %-12s %8d руб.",
                    brand, processor, memory, monitor, price);
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
