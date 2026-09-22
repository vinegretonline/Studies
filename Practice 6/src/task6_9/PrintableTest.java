package task6_9;

public class PrintableTest {

    public static void main(String[] args) {
        Printable[] items = {
                new Book("Война и мир", "Толстой Л. Н.", 1869),
                new Magazine("Наука и жизнь", 4),
                new Book("Мёртвые души", "Гоголь Н. В.", 1842),
                new Magazine("Радио", 11)
        };

        System.out.println("Вызов print() для каждого объекта массива:");
        for (int i = 0; i < items.length; i++) {
            items[i].print();
        }

        Shop shop = new Shop("Книжный мир", "ул. Ленина, 5", 10);
        for (int i = 0; i < items.length; i++) {
            shop.add(items[i]);
        }

        System.out.println();
        shop.print();
        System.out.println("Товары магазина:");
        shop.printGoods();
    }
}
