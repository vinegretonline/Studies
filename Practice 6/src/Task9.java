public class Task9 {

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

        public void print() {
            System.out.println("Книга: " + title + ", автор " + author + ", " + year + " год");
        }
    }

    static class Magazine implements Printable {

        private String title;
        private int number;

        public Magazine(String title, int number) {
            this.title = title;
            this.number = number;
        }

        public void print() {
            System.out.println("Журнал: " + title + ", выпуск № " + number);
        }
    }

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
    }
}
