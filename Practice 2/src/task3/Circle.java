package task3;

public class Circle {

    private Point center;
    private double radius;

    public Circle(double x, double y, double radius) {
        center = new Point(x, y);
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return "Окружность с центром " + center + " и радиусом " + radius;
    }
}
