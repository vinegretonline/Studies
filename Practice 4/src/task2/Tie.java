package task2;

public class Tie extends Clothes implements MenClothing {

    public Tie(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    public void dressMan() {
        System.out.println("Надеваем галстук. " + this);
    }
}
