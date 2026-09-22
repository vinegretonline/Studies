package task41_6;

public class Employer {

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
