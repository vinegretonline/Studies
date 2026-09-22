public class Task11 {

    static class Learner {

        protected String fullName;
        protected int age;

        public Learner(String fullName, int age) {
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

    static class Pupil extends Learner {

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

    static class Student extends Learner {

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
