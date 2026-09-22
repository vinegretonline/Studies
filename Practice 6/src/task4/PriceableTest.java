package task4;

public class PriceableTest {

    public static void main(String[] args) {
        Priceable[] items = {
                new Book("Война и мир", 890),
                new Ticket("Москва - Казань", 3200, 450),
                new Laptop("Lenovo IdeaPad", 55000, 15),
                new Book("Мастер и Маргарита", 1150)
        };

        double total = 0;
        System.out.println("Цены товаров:");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%-35s %10.2f руб.%n", items[i], items[i].getPrice());
            total = total + items[i].getPrice();
        }
        System.out.printf("Итого: %.2f руб.%n", total);
    }
}
