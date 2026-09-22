package task3;

public class Planet implements Nameable {

    private String name;
    private double diameter;

    public Planet(String name, double diameter) {
        this.name = name;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public double getDiameter() {
        return diameter;
    }
}
