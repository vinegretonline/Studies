package task41_7;

public class Студент extends Учащийся {

    private String university;
    private int course;

    public Студент(String fullName, int age, String university, int course) {
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
