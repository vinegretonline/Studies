package task41_9;

public class FurnitureShop {

    private Furniture[] furniture;
    private int count;

    public FurnitureShop(int size) {
        furniture = new Furniture[size];
        count = 0;
    }

    public void add(Furniture item) {
        if (count < furniture.length) {
            furniture[count] = item;
            count++;
        } else {
            System.out.println("В магазине нет места");
        }
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ") " + furniture[i]);
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total = total + furniture[i].getPrice();
        }
        return total;
    }

    public Furniture findCheapest() {
        Furniture cheapest = furniture[0];
        for (int i = 1; i < count; i++) {
            if (furniture[i].getPrice() < cheapest.getPrice()) {
                cheapest = furniture[i];
            }
        }
        return cheapest;
    }

    public static void main(String[] args) {
        FurnitureShop shop = new FurnitureShop(5);

        shop.add(new Table("Стол", "дуб", 18500, 4));
        shop.add(new Chair("Стул", "бук", 4200, true));
        shop.add(new Wardrobe("Шкаф", "ЛДСП", 27300, 3));
        shop.add(new Chair("Табурет", "сосна", 1500, false));

        System.out.println("Мебель в магазине:");
        shop.printAll();

        System.out.printf("Общая стоимость: %.2f руб.%n", shop.getTotalPrice());
        System.out.println("Самая дешёвая: " + shop.findCheapest().getName());
    }
}
