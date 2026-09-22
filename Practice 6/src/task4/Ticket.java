package task4;

public class Ticket implements Priceable {

    private String route;
    private double basePrice;
    private double tax;

    public Ticket(String route, double basePrice, double tax) {
        this.route = route;
        this.basePrice = basePrice;
        this.tax = tax;
    }

    public double getPrice() {
        return basePrice + tax;
    }

    public String toString() {
        return "Билет " + route;
    }
}
