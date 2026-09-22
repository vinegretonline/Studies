public class Task3 {

    interface Nameable {

        String getName();
    }

    static class Planet implements Nameable {

        private String name;
        private double diameter;

        public Planet(String name, double diameter) {
            this.name = name;
            this.diameter = diameter;
        }

        public String getName() {
            return name;
        }

        public double getDiameter() {
            return diameter;
        }
    }

    static class Car implements Nameable {

        private String brand;
        private String model;

        public Car(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public String getName() {
            return brand + " " + model;
        }
    }

    static class Animal implements Nameable {

        private String nickname;
        private String species;

        public Animal(String nickname, String species) {
            this.nickname = nickname;
            this.species = species;
        }

        public String getName() {
            return nickname + " (" + species + ")";
        }
    }

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
