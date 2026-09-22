package task6_9;

public class Book implements Printable {

    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void print() {
        System.out.println("Книга: " + title + ", автор " + author + ", " + year + " год");
    }
}
