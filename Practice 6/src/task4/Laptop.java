package task4;

public class Laptop implements Priceable {

    private String model;
    private double price;
    private int discount;

    public Laptop(String model, double price, int discount) {
        this.model = model;
        this.price = price;
        this.discount = discount;
    }

    public double getPrice() {
        return price - price * discount / 100;
    }

    public String toString() {
        return "Ноутбук " + model + " (скидка " + discount + "%)";
    }
}
