package task13;

public class LengthWatcher implements Observer {

    private int limit;

    public LengthWatcher(int limit) {
        this.limit = limit;
    }

    public void update(String operation, String newValue) {
        if (newValue.length() > limit) {
            System.out.println("[Наблюдатель длины] строка длиннее " + limit
                    + " символов (сейчас " + newValue.length() + ")");
        }
    }
}
