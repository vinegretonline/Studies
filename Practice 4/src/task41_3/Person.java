package task41_3;

public class Person {

    private String fullName;
    private int age;

    public Person() {
        fullName = "Без имени";
        age = 0;
    }

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public void move() {
        System.out.println(fullName + " идёт");
    }

    public void talk() {
        System.out.println(fullName + " говорит");
    }

    public String toString() {
        return "Person[" + fullName + ", " + age + " лет]";
    }

    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Иванов Иван", 20);

        System.out.println(p1);
        System.out.println(p2);

        p1.move();
        p1.talk();
        p2.move();
        p2.talk();
    }
}
