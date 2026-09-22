package task4;

public class Computer {

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

    public Processor getProcessor() {
        return processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public int getPrice() {
        return price;
    }

    public void printInfo() {
        System.out.println("Компьютер " + brand);
        System.out.println("  Процессор: " + processor);
        System.out.println("  Память:    " + memory);
        System.out.println("  Монитор:   " + monitor);
        System.out.println("  Цена:      " + price + " руб.");
    }

    public static void main(String[] args) {
        Computer c1 = new Computer(Brand.ASUS,
                new Processor("Intel Core i5", 6, 3.2),
                new Memory(16, "DDR4"),
                new Monitor(24, 1920, 1080),
                72000);

        Computer c2 = new Computer(Brand.APPLE,
                new Processor("Apple M3", 8, 4.0),
                new Memory(8, "LPDDR5"),
                new Monitor(13.6, 2560, 1664),
                124000);

        c1.printInfo();
        System.out.println();
        c2.printInfo();
    }
}
