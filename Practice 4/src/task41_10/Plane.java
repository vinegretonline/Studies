package task41_10;

public class Plane extends Vehicle {

    public Plane() {
        super("Самолёт", 850, 180, 20, 120);
    }

    public double getPassengerPrice() {
        return 4500;
    }

    public double getCargoPrice() {
        return 9000;
    }
}
