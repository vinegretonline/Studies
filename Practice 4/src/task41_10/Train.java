package task41_10;

public class Train extends Vehicle {

    public Train() {
        super("Поезд", 120, 600, 400, 45);
    }

    public double getPassengerPrice() {
        return 1200;
    }

    public double getCargoPrice() {
        return 800;
    }
}
