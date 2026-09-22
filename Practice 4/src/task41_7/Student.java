package task41_7;

public class Student extends Learner {

    private String university;
    private int course;

    public Student(String fullName, int age, String university, int course) {
        super(fullName, age);
        this.university = university;
        this.course = course;
    }

    public String getUniversity() {
        return university;
    }

    public int getCourse() {
        return course;
    }

    public String toString() {
        return super.toString() + ", " + university + ", " + course + " курс";
    }
}
