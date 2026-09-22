package task41_1;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    public double getSide() {
        return width;
    }

    public String getType() {
        return "Квадрат";
    }

    public String toString() {
        return getType() + " со стороной " + getSide();
    }
}
