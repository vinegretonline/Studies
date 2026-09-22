package task3;

public class Product {

    private String name;
    private double price;
    private Catalog catalog;

    public Product(String name, double price, Catalog catalog) {
        this.name = name;
        this.price = price;
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public String toString() {
        return String.format("%-22s %10.2f руб.", name, price);
    }
}
