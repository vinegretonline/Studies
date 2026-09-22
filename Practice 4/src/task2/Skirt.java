package task2;

public class Skirt extends Clothes implements WomenClothing {

    public Skirt(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    public void dressWomen() {
        System.out.println("Надеваем юбку. " + this);
    }
}
