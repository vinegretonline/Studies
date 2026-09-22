public class Task6 {

    static class Phone {

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

    public static void main(String[] args) {
        Phone p1 = new Phone("+79001112233", "iPhone 15", 171);
        Phone p2 = new Phone("+79004445566", "Samsung S24");
        Phone p3 = new Phone();

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        System.out.println("Номер первого телефона: " + p1.getNumber());
        System.out.println("Номер второго телефона: " + p2.getNumber());

        p1.receiveCall("Иван");
        p2.receiveCall("Мария");
        p3.receiveCall("Неизвестный");

        p1.receiveCall("Пётр", "+79007778899");

        p1.sendMessage("+79001112233", "+79004445566", "+79007778899");
    }
}
