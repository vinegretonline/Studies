package task2;

public class TShirt extends Clothes implements MenClothing, WomenClothing {

    public TShirt(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    public void dressMan() {
        System.out.println("Надеваем мужскую футболку. " + this);
    }

    public void dressWomen() {
        System.out.println("Надеваем женскую футболку. " + this);
    }
}
