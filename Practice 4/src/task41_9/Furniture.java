package task41_9;

public abstract class Furniture {

    protected String name;
    protected String material;
    protected double price;

    public Furniture(String name, String material, double price) {
        this.name = name;
        this.material = material;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getPurpose();

    public String toString() {
        return String.format("%-12s %-10s %10.2f руб. - %s", name, material, price, getPurpose());
    }
}
