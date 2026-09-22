public class Task2 {

    enum ClothingSize {

        XXS(32) {
            public String getDescription() {
                return "Детский размер";
            }
        },
        XS(34),
        S(36),
        M(38),
        L(40);

        private int euroSize;

        ClothingSize(int euroSize) {
            this.euroSize = euroSize;
        }

        public int getEuroSize() {
            return euroSize;
        }

        public String getDescription() {
            return "Взрослый размер";
        }
    }

    interface MenClothing {

        void dressMan();
    }

    interface WomenClothing {

        void dressWomen();
    }

    abstract static class Clothes {

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

    static class TShirt extends Clothes implements MenClothing, WomenClothing {

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

    static class Pants extends Clothes implements MenClothing, WomenClothing {

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

    static class Skirt extends Clothes implements WomenClothing {

        public Skirt(ClothingSize size, double cost, String color) {
            super(size, cost, color);
        }

        public void dressWomen() {
            System.out.println("Надеваем юбку. " + this);
        }
    }

    static class Tie extends Clothes implements MenClothing {

        public Tie(ClothingSize size, double cost, String color) {
            super(size, cost, color);
        }

        public void dressMan() {
            System.out.println("Надеваем галстук. " + this);
        }
    }

    static class Atelier {

        public void dressMan(Clothes[] clothes) {
            System.out.println("Мужская одежда:");
            for (int i = 0; i < clothes.length; i++) {
                if (clothes[i] instanceof MenClothing) {
                    MenClothing item = (MenClothing) clothes[i];
                    item.dressMan();
                }
            }
        }

        public void dressWomen(Clothes[] clothes) {
            System.out.println("Женская одежда:");
            for (int i = 0; i < clothes.length; i++) {
                if (clothes[i] instanceof WomenClothing) {
                    WomenClothing item = (WomenClothing) clothes[i];
                    item.dressWomen();
                }
            }
        }
    }

    public static void main(String[] args) {
        Clothes[] clothes = {
                new TShirt(ClothingSize.M, 1500, "белый"),
                new Pants(ClothingSize.L, 3200, "синий"),
                new Skirt(ClothingSize.S, 2700, "красный"),
                new Tie(ClothingSize.XXS, 990, "чёрный")
        };

        Atelier atelier = new Atelier();
        atelier.dressMan(clothes);
        System.out.println();
        atelier.dressWomen(clothes);
    }
}
