package task41_7;

public class Школьник extends Учащийся {

    private int classNumber;

    public Школьник(String fullName, int age, int classNumber) {
        super(fullName, age);
        this.classNumber = classNumber;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public String toString() {
        return super.toString() + ", " + classNumber + " класс";
    }
}
