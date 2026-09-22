package task2;

public class Pants extends Clothes implements MenClothing, WomenClothing {

    public Pants(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    public void dressMan() {
        System.out.println("Надеваем мужские штаны. " + this);
    }

    public void dressWomen() {
        System.out.println("Надеваем женские штаны. " + this);
    }
}
