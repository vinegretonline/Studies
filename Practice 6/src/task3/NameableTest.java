package task3;

public class NameableTest {

    public static void main(String[] args) {
        Nameable[] objects = {
                new Planet("Марс", 6779),
                new Car("Lada", "Vesta"),
                new Animal("Барсик", "кот"),
                new Planet("Юпитер", 139820),
                new Animal("Бобик", "пёс")
        };

        System.out.println("Имена всех объектов:");
        for (int i = 0; i < objects.length; i++) {
            System.out.println((i + 1) + ") " + objects[i].getName());
        }
    }
}
