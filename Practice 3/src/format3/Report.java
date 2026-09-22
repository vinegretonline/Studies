package format3;

public class Report {

    public static void generateReport(Employee[] employees) {
        System.out.println("+----+----------------------+--------------+");
        System.out.printf("| %-2s | %-20s | %12s |%n", "№", "Сотрудник", "Зарплата");
        System.out.println("+----+----------------------+--------------+");

        double total = 0;
        for (int i = 0; i < employees.length; i++) {
            System.out.printf("| %-2d | %-20s | %12.2f |%n",
                    i + 1, employees[i].getFullname(), employees[i].getSalary());
            total = total + employees[i].getSalary();
        }

        System.out.println("+----+----------------------+--------------+");
        System.out.printf("| %-25s | %12.2f |%n", "Итого", total);
        System.out.printf("| %-25s | %12.2f |%n", "Средняя зарплата", total / employees.length);
        System.out.println("+---------------------------+--------------+");
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Петров В. В.", 75000),
                new Employee("Иванова А. С.", 128500.5),
                new Employee("Сидоров К. П.", 94300.75),
                new Employee("Волкова М. И.", 210000)
        };

        generateReport(employees);
    }
}
