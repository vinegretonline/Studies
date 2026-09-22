package task2;

public class Atelier {

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
