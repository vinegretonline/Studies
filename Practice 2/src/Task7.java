public class Task7 {

    static class Book {

        private String author;
        private String title;
        private int year;

        public Book(String author, String title, int year) {
            this.author = author;
            this.title = title;
            this.year = year;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String toString() {
            return author + ". " + title + " (" + year + ")";
        }
    }

    static class BookShelf {

        private Book[] books;
        private int count;

        public BookShelf(int size) {
            books = new Book[size];
            count = 0;
        }

        public void addBook(Book book) {
            if (count < books.length) {
                books[count] = book;
                count++;
            } else {
                System.out.println("На полке нет места");
            }
        }

        public Book getNewest() {
            Book newest = books[0];
            for (int i = 1; i < count; i++) {
                if (books[i].getYear() > newest.getYear()) {
                    newest = books[i];
                }
            }
            return newest;
        }

        public Book getOldest() {
            Book oldest = books[0];
            for (int i = 1; i < count; i++) {
                if (books[i].getYear() < oldest.getYear()) {
                    oldest = books[i];
                }
            }
            return oldest;
        }

        public void sortByYear() {
            for (int i = 0; i < count - 1; i++) {
                for (int j = 0; j < count - 1 - i; j++) {
                    if (books[j].getYear() > books[j + 1].getYear()) {
                        Book temp = books[j];
                        books[j] = books[j + 1];
                        books[j + 1] = temp;
                    }
                }
            }
        }

        public void printAll() {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ") " + books[i]);
            }
        }
    }

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
