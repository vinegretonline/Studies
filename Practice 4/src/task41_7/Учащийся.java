package task41_7;

public class Учащийся {

    protected String fullName;
    protected int age;

    public Учащийся(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return fullName + ", " + age + " лет";
    }
}
