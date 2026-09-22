package task6_9;

public class Magazine implements Printable {

    private String title;
    private int number;

    public Magazine(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void print() {
        System.out.println("Журнал: " + title + ", выпуск № " + number);
    }
}
