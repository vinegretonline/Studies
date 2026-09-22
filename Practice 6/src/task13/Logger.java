package task13;

public class Logger implements Observer {

    private String name;

    public Logger(String name) {
        this.name = name;
    }

    public void update(String operation, String newValue) {
        System.out.println("[" + name + "] операция " + operation + ", строка стала: " + newValue);
    }
}
