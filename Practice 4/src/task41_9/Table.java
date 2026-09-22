package task41_9;

public class Table extends Furniture {

    private int legs;

    public Table(String name, String material, double price, int legs) {
        super(name, material, price);
        this.legs = legs;
    }

    public int getLegs() {
        return legs;
    }

    public String getPurpose() {
        return "за ним работают и едят, ножек: " + legs;
    }
}
