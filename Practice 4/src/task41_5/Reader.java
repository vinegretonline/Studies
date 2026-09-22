package task41_5;

public class Reader {

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
