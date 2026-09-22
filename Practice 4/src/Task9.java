public class Task9 {

    static class Book {

        private String name;
        private String author;

        public Book(String name, String author) {
            this.name = name;
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String toString() {
            return name;
        }
    }

    static class Reader {

        private String fullName;
        private int cardNumber;
        private String faculty;
        private String birthDate;
        private String phone;

        public Reader(String fullName, int cardNumber, String faculty, String birthDate, String phone) {
            this.fullName = fullName;
            this.cardNumber = cardNumber;
            this.faculty = faculty;
            this.birthDate = birthDate;
            this.phone = phone;
        }

        public String getFullName() {
            return fullName;
        }

        public int getCardNumber() {
            return cardNumber;
        }

        public void takeBook(int count) {
            System.out.println(fullName + " взял " + count + " книги");
        }

        public void takeBook(String... titles) {
            System.out.print(fullName + " взял книги: ");
            printTitles(titles);
        }

        public void takeBook(Book... books) {
            System.out.print(fullName + " взял книги: ");
            printBooks(books);
        }

        public void returnBook(int count) {
            System.out.println(fullName + " вернул " + count + " книги");
        }

        public void returnBook(String... titles) {
            System.out.print(fullName + " вернул книги: ");
            printTitles(titles);
        }

        public void returnBook(Book... books) {
            System.out.print(fullName + " вернул книги: ");
            printBooks(books);
        }

        private void printTitles(String[] titles) {
            for (int i = 0; i < titles.length; i++) {
                System.out.print(titles[i]);
                if (i < titles.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        private void printBooks(Book[] books) {
            for (int i = 0; i < books.length; i++) {
                System.out.print(books[i].getName());
                if (i < books.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        public String toString() {
            return fullName + ", билет № " + cardNumber + ", " + faculty
                    + ", дата рождения " + birthDate + ", телефон " + phone;
        }
    }

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
