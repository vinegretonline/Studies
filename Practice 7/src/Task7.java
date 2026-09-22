public class Task7 {

    interface Printable {

        void print();

        String getTitle();
    }

    static class Book implements Printable {

        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void print() {
            System.out.println("Книга: " + title + ", автор " + author);
        }
    }

    static class Magazine implements Printable {

        private String title;
        private int number;

        public Magazine(String title, int number) {
            this.title = title;
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public int getNumber() {
            return number;
        }

        public void print() {
            System.out.println("Журнал: " + title + ", выпуск № " + number);
        }

        public static void printMagazines(Printable[] printable) {
            System.out.println("Названия журналов:");
            for (int i = 0; i < printable.length; i++) {
                if (printable[i] instanceof Magazine) {
                    Magazine magazine = (Magazine) printable[i];
                    System.out.println("  " + magazine.getTitle() + " № " + magazine.getNumber());
                }
            }
        }
    }

    public static void main(String[] args) {
        Printable[] items = {
                new Book("Война и мир", "Толстой Л. Н."),
                new Magazine("Наука и жизнь", 4),
                new Book("Мёртвые души", "Гоголь Н. В."),
                new Magazine("Радио", 11)
        };

        System.out.println("Все объекты массива:");
        for (int i = 0; i < items.length; i++) {
            items[i].print();
        }

        System.out.println();
        Magazine.printMagazines(items);
    }
}
