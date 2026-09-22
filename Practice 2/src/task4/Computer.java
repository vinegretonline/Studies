package task4;

public class Computer {

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
