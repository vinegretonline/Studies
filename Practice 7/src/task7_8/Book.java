package task7_8;

public class Book implements Printable {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void print() {
        System.out.println("Книга: " + title + ", автор " + author);
    }

    public static void printBooks(Printable[] printable) {
        System.out.println("Названия книг:");
        for (int i = 0; i < printable.length; i++) {
            if (printable[i] instanceof Book) {
                Book book = (Book) printable[i];
                System.out.println("  " + book.getTitle() + " (" + book.getAuthor() + ")");
            }
        }
    }
}
