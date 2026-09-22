public class Task10 {

    static class Employer {

        protected String firstName;
        protected String lastName;
        protected double income;
        protected int workedDays;

        public Employer(String firstName, String lastName, double income, int workedDays) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.income = income;
            this.workedDays = workedDays;
        }

        public double getIncome() {
            if (workedDays >= 220) {
                return income * 12;
            }
            return income;
        }

        public String toString() {
            return firstName + " " + lastName;
        }
    }

    static class Manager extends Employer {

        private double averageSum;

        public Manager(String firstName, String lastName, double income, int workedDays, double averageSum) {
            super(firstName, lastName, income, workedDays);
            this.averageSum = averageSum;
        }

        public double getAverageSum() {
            return averageSum;
        }

        public double getIncome() {
            return super.getIncome() + averageSum;
        }

        public String toString() {
            return super.toString() + " (менеджер)";
        }
    }

    public static void main(String[] args) {
        Employer employer = new Manager("Пётр", "Смирнов", 60000, 230, 45000);
        System.out.println(employer + ": доход " + employer.getIncome());

        Employer[] staff = {
                new Employer("Иван", "Петров", 70000, 230),
                new Employer("Анна", "Волкова", 55000, 180),
                new Manager("Пётр", "Смирнов", 60000, 230, 45000),
                new Manager("Мария", "Козлова", 65000, 150, 30000)
        };

        for (int i = 0; i < staff.length; i++) {
            System.out.printf("%-25s доход %12.2f%n", staff[i], staff[i].getIncome());
        }
    }
}
