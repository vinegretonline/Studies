package task2;

public abstract class Clothes {

    protected ClothingSize size;
    protected double cost;
    protected String color;

    public Clothes(ClothingSize size, double cost, String color) {
        this.size = size;
        this.cost = cost;
        this.color = color;
    }

    public ClothingSize getSize() {
        return size;
    }

    public double getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return getClass().getSimpleName() + ": размер " + size + " (евро " + size.getEuroSize()
                + ", " + size.getDescription() + "), цвет " + color + ", цена " + cost + " руб.";
    }
}
