public class Task8 {

    interface Printable {

        void print();
    }

    static class Shop implements Printable {

        private String name;
        private String address;
        private int goodsCount;

        public Shop(String name, String address, int goodsCount) {
            this.name = name;
            this.address = address;
            this.goodsCount = goodsCount;
        }

        public String getName() {
            return name;
        }

        public void print() {
            System.out.println("Магазин \"" + name + "\", адрес: " + address + ", товаров: " + goodsCount);
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop("Книжный мир", "ул. Ленина, 5", 120);
        shop.print();

        Printable printable = new Shop("Пресса", "пр. Мира, 18", 45);
        printable.print();
    }
}
