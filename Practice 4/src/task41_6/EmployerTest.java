package task41_6;

public class EmployerTest {

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
