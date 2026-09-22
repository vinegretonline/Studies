package task41_7;

public class Pupil extends Learner {

    private int classNumber;

    public Pupil(String fullName, int age, int classNumber) {
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
