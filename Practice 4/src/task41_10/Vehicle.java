package task41_10;

public abstract class Vehicle {

    protected String name;
    protected double speed;
    protected int maxPassengers;
    protected double maxCargo;
    protected double pricePerKm;

    public Vehicle(String name, double speed, int maxPassengers, double maxCargo, double pricePerKm) {
        this.name = name;
        this.speed = speed;
        this.maxPassengers = maxPassengers;
        this.maxCargo = maxCargo;
        this.pricePerKm = pricePerKm;
    }

    public String getName() {
        return name;
    }

    public double getTime(double distance) {
        return distance / speed;
    }

    public double getCost(double distance, int passengers, double cargo) {
        if (passengers > maxPassengers) {
            System.out.println(name + ": слишком много пассажиров");
            return -1;
        }
        if (cargo > maxCargo) {
            System.out.println(name + ": слишком много груза");
            return -1;
        }
        return distance * pricePerKm + passengers * getPassengerPrice() + cargo * getCargoPrice();
    }

    public abstract double getPassengerPrice();

    public abstract double getCargoPrice();

    public String toString() {
        return name + " (скорость " + speed + " км/ч, до " + maxPassengers
                + " пассажиров, до " + maxCargo + " т груза)";
    }
}
