package task10;

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
