package task41_2;

public class PhoneTest {

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
