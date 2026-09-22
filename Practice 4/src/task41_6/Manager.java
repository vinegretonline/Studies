package task41_6;

public class Manager extends Employer {

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
