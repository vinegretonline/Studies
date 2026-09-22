public class Task14 {

    abstract static class Vehicle {

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

    static class Car extends Vehicle {

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

    static class Plane extends Vehicle {

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

    static class Train extends Vehicle {

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

    static class Ship extends Vehicle {

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

    public static void main(String[] args) {
        Vehicle[] vehicles = {new Car(), new Plane(), new Train(), new Ship()};

        double distance = 1200;
        int passengers = 4;
        double cargo = 0.4;

        System.out.println("Расстояние: " + distance + " км, пассажиров: " + passengers
                + ", груза: " + cargo + " т");
        System.out.println("+-------------+------------+----------------+");
        System.out.printf("| %-11s | %10s | %14s |%n", "Транспорт", "Время, ч", "Стоимость");
        System.out.println("+-------------+------------+----------------+");

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle vehicle = vehicles[i];
            double cost = vehicle.getCost(distance, passengers, cargo);
            System.out.printf("| %-11s | %10.2f | %14.2f |%n",
                    vehicle.getName(), vehicle.getTime(distance), cost);
        }
        System.out.println("+-------------+------------+----------------+");

        System.out.println();
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println(vehicles[i]);
        }
    }
}
