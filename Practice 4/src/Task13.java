public class Task13 {

    abstract static class Furniture {

        protected String name;
        protected String material;
        protected double price;

        public Furniture(String name, String material, double price) {
            this.name = name;
            this.material = material;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public abstract String getPurpose();

        public String toString() {
            return String.format("%-12s %-10s %10.2f руб. - %s", name, material, price, getPurpose());
        }
    }

    static class Table extends Furniture {

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

    static class Chair extends Furniture {

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

    static class Wardrobe extends Furniture {

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

    static class FurnitureShop {

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
