package task6;

public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getLength() {
        return 2 * Math.PI * radius;
    }

    public boolean isEqual(Circle other) {
        return radius == other.getRadius();
    }

    public String toString() {
        return "Окружность с радиусом " + radius;
    }
}
