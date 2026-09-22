package task41_9;

public class Wardrobe extends Furniture {

    private int doors;

    public Wardrobe(String name, String material, double price, int doors) {
        super(name, material, price);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public String getPurpose() {
        return "в нём хранят вещи, дверей: " + doors;
    }
}
