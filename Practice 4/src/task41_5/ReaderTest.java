package task41_5;

public class ReaderTest {

    public static void main(String[] args) {
        Reader[] readers = {
                new Reader("Петров В. В.", 1001, "ИВТ", "12.05.2004", "+79001112233"),
                new Reader("Иванова А. С.", 1002, "ФИЯ", "03.11.2003", "+79004445566")
        };

        for (int i = 0; i < readers.length; i++) {
            System.out.println(readers[i]);
        }

        System.out.println();

        readers[0].takeBook(3);
        readers[0].takeBook("Приключения", "Словарь", "Энциклопедия");
        readers[0].takeBook(new Book("Приключения", "Стивенсон"),
                new Book("Словарь", "Ожегов"),
                new Book("Энциклопедия", "Брокгауз"));

        System.out.println();

        readers[1].returnBook(2);
        readers[1].returnBook("Война и мир", "Мёртвые души");
        readers[1].returnBook(new Book("Война и мир", "Толстой"),
                new Book("Мёртвые души", "Гоголь"));
    }
}
