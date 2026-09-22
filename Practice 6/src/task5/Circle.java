package task5;

public class Circle {

    protected Point center;
    protected double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public String toString() {
        return "Окружность с центром " + center + " и радиусом " + radius;
    }
}
