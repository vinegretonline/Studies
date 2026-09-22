package task7;

public class BookTest {

    public static void main(String[] args) {
        Book book = new Book("Пушкин А. С.", "Капитанская дочка", 1836);
        System.out.println(book);
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("Название: " + book.getTitle());
        System.out.println("Год: " + book.getYear());

        book.setYear(1837);
        System.out.println("После изменения года: " + book);

        BookShelf shelf = new BookShelf(5);
        shelf.addBook(new Book("Толстой Л. Н.", "Война и мир", 1869));
        shelf.addBook(new Book("Пушкин А. С.", "Капитанская дочка", 1836));
        shelf.addBook(new Book("Булгаков М. А.", "Мастер и Маргарита", 1967));
        shelf.addBook(new Book("Гоголь Н. В.", "Мёртвые души", 1842));

        System.out.println("Книги на полке:");
        shelf.printAll();

        System.out.println("Самая поздняя: " + shelf.getNewest());
        System.out.println("Самая ранняя: " + shelf.getOldest());

        shelf.sortByYear();
        System.out.println("После сортировки по году:");
        shelf.printAll();
    }
}
