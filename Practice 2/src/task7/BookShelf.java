package task7;

public class BookShelf {

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
