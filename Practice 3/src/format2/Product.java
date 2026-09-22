package format2;

public class Product {

    private String name;
    private double priceRub;

    public Product(String name, double priceRub) {
        this.name = name;
        this.priceRub = priceRub;
    }

    public String getName() {
        return name;
    }

    public double getPriceRub() {
        return priceRub;
    }
}
