import java.util.ArrayList;

public class Task13 {

    interface Observer {

        void update(String operation, String newValue);
    }

    static class Logger implements Observer {

        private String name;

        public Logger(String name) {
            this.name = name;
        }

        public void update(String operation, String newValue) {
            System.out.println("[" + name + "] операция " + operation + ", строка стала: " + newValue);
        }
    }

    static class LengthWatcher implements Observer {

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

    static class ObservableStringBuilder {

        private StringBuilder builder;
        private ArrayList<Observer> observers;

        public ObservableStringBuilder() {
            builder = new StringBuilder();
            observers = new ArrayList<Observer>();
        }

        public ObservableStringBuilder(String text) {
            builder = new StringBuilder(text);
            observers = new ArrayList<Observer>();
        }

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        private void notifyObservers(String operation) {
            for (int i = 0; i < observers.size(); i++) {
                observers.get(i).update(operation, builder.toString());
            }
        }

        public ObservableStringBuilder append(String text) {
            builder.append(text);
            notifyObservers("append");
            return this;
        }

        public ObservableStringBuilder insert(int offset, String text) {
            builder.insert(offset, text);
            notifyObservers("insert");
            return this;
        }

        public ObservableStringBuilder delete(int start, int end) {
            builder.delete(start, end);
            notifyObservers("delete");
            return this;
        }

        public ObservableStringBuilder reverse() {
            builder.reverse();
            notifyObservers("reverse");
            return this;
        }

        public int length() {
            return builder.length();
        }

        public String toString() {
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        ObservableStringBuilder text = new ObservableStringBuilder("Начало");

        Logger logger = new Logger("Журнал");
        text.addObserver(logger);
        text.addObserver(new LengthWatcher(15));

        text.append(" строки");
        text.append(" и ещё немного текста");
        text.insert(0, ">> ");
        text.delete(0, 3);
        text.reverse();

        System.out.println();
        System.out.println("Убираем журнал из наблюдателей");
        text.removeObserver(logger);
        text.append("!");

        System.out.println("Итог: " + text);
    }
}
