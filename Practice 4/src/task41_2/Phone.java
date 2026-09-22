package task41_2;

public class Phone {

    private String number;
    private String model;
    private double weight;

    public Phone() {
    }

    public Phone(String number, String model) {
        this.number = number;
        this.model = model;
    }

    public Phone(String number, String model, double weight) {
        this(number, model);
        this.weight = weight;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }

    public void receiveCall(String name) {
        System.out.println("Звонит " + name);
    }

    public void receiveCall(String name, String callerNumber) {
        System.out.println("Звонит " + name + ", номер " + callerNumber);
    }

    public void sendMessage(String... numbers) {
        System.out.println("Сообщение отправлено на номера:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("  " + numbers[i]);
        }
    }

    public String toString() {
        return "Телефон " + model + ", номер " + number + ", вес " + weight + " г";
    }
}
