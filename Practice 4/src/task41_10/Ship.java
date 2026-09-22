package task41_10;

public class Ship extends Vehicle {

    public Ship() {
        super("Корабль", 40, 1000, 5000, 30);
    }

    public double getPassengerPrice() {
        return 2500;
    }

    public double getCargoPrice() {
        return 300;
    }
}
