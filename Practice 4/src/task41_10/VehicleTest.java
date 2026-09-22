package task41_10;

public class VehicleTest {

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
