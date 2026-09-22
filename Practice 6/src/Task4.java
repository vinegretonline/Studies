public class Task4 {

    interface Priceable {

        double getPrice();
    }

    static class Book implements Priceable {

        private String title;
        private double price;

        public Book(String title, double price) {
            this.title = title;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public double getPrice() {
            return price;
        }

        public String toString() {
            return "Книга \"" + title + "\"";
        }
    }

    static class Ticket implements Priceable {

        private String route;
        private double basePrice;
        private double tax;

        public Ticket(String route, double basePrice, double tax) {
            this.route = route;
            this.basePrice = basePrice;
            this.tax = tax;
        }

        public double getPrice() {
            return basePrice + tax;
        }

        public String toString() {
            return "Билет " + route;
        }
    }

    static class Laptop implements Priceable {

        private String model;
        private double price;
        private int discount;

        public Laptop(String model, double price, int discount) {
            this.model = model;
            this.price = price;
            this.discount = discount;
        }

        public double getPrice() {
            return price - price * discount / 100;
        }

        public String toString() {
            return "Ноутбук " + model + " (скидка " + discount + "%)";
        }
    }

    public static void main(String[] args) {
        Priceable[] items = {
                new Book("Война и мир", 890),
                new Ticket("Москва - Казань", 3200, 450),
                new Laptop("Lenovo IdeaPad", 55000, 15),
                new Book("Мастер и Маргарита", 1150)
        };

        double total = 0;
        System.out.println("Цены товаров:");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%-35s %10.2f руб.%n", items[i], items[i].getPrice());
            total = total + items[i].getPrice();
        }
        System.out.printf("Итого: %.2f руб.%n", total);
    }
}
