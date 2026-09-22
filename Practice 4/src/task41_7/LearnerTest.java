package task41_7;

public class LearnerTest {

    public static void main(String[] args) {
        Learner[] learners = {
                new Pupil("Кузнецов Дима", 14, 8),
                new Student("Петров Иван", 20, "МИРЭА", 2),
                new Pupil("Соколова Катя", 16, 10),
                new Student("Волкова Аня", 19, "МГУ", 1)
        };

        System.out.println("Школьники:");
        for (int i = 0; i < learners.length; i++) {
            if (learners[i] instanceof Pupil) {
                System.out.println("  " + learners[i]);
            }
        }

        System.out.println("Студенты:");
        for (int i = 0; i < learners.length; i++) {
            if (learners[i] instanceof Student) {
                System.out.println("  " + learners[i]);
            }
        }
    }
}
