package task41_7;

public class УчащийсяTest {

    public static void main(String[] args) {
        Учащийся[] все = {
                new Школьник("Кузнецов Дима", 14, 8),
                new Студент("Петров Иван", 20, "МИРЭА", 2),
                new Школьник("Соколова Катя", 16, 10),
                new Студент("Волкова Аня", 19, "МГУ", 1)
        };

        System.out.println("Школьники:");
        for (int i = 0; i < все.length; i++) {
            if (все[i] instanceof Школьник) {
                System.out.println("  " + все[i]);
            }
        }

        System.out.println("Студенты:");
        for (int i = 0; i < все.length; i++) {
            if (все[i] instanceof Студент) {
                System.out.println("  " + все[i]);
            }
        }
    }
}
