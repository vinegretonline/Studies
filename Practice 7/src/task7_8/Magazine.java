package task7_8;

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
