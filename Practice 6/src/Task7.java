public class Task7 {

    interface Printable {

        void print();
    }

    static class Book implements Printable {

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

    public static void main(String[] args) {
        Book book = new Book("Война и мир", "Толстой Л. Н.", 1869);
        book.print();

        Printable printable = new Book("Мёртвые души", "Гоголь Н. В.", 1842);
        printable.print();
    }
}
