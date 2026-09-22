package task6_9;

public class Shop implements Printable {

    private String name;
    private String address;
    private Printable[] goods;
    private int count;

    public Shop(String name, String address, int size) {
        this.name = name;
        this.address = address;
        goods = new Printable[size];
        count = 0;
    }

    public void add(Printable item) {
        if (count < goods.length) {
            goods[count] = item;
            count++;
        }
    }

    public void print() {
        System.out.println("Магазин \"" + name + "\", адрес: " + address + ", товаров: " + count);
    }

    public void printGoods() {
        for (int i = 0; i < count; i++) {
            goods[i].print();
        }
    }
}
