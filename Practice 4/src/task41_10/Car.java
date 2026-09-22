package task41_10;

public class Car extends Vehicle {

    public Car() {
        super("Автомобиль", 90, 4, 0.5, 15);
    }

    public double getPassengerPrice() {
        return 500;
    }

    public double getCargoPrice() {
        return 2000;
    }
}
