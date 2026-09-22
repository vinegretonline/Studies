package task41_9;

public class Chair extends Furniture {

    private boolean soft;

    public Chair(String name, String material, double price, boolean soft) {
        super(name, material, price);
        this.soft = soft;
    }

    public boolean isSoft() {
        return soft;
    }

    public String getPurpose() {
        if (soft) {
            return "на нём сидят, мягкий";
        }
        return "на нём сидят, жёсткий";
    }
}
