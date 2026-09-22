package task7_8;

public class PrintableTest {

    public static void main(String[] args) {
        Printable[] items = {
                new Book("Война и мир", "Толстой Л. Н."),
                new Magazine("Наука и жизнь", 4),
                new Book("Мёртвые души", "Гоголь Н. В."),
                new Magazine("Радио", 11),
                new Book("Капитанская дочка", "Пушкин А. С.")
        };

        System.out.println("Все объекты массива:");
        for (int i = 0; i < items.length; i++) {
            items[i].print();
        }

        System.out.println();
        Book.printBooks(items);

        System.out.println();
        Magazine.printMagazines(items);
    }
}
