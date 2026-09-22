import java.util.ArrayList;

public class Task12 {

    static class UndoStringBuilder {

        private StringBuilder builder;
        private ArrayList<String> history;

        public UndoStringBuilder() {
            builder = new StringBuilder();
            history = new ArrayList<String>();
        }

        public UndoStringBuilder(String text) {
            builder = new StringBuilder(text);
            history = new ArrayList<String>();
        }

        private void saveState() {
            history.add(builder.toString());
        }

        public UndoStringBuilder append(String text) {
            saveState();
            builder.append(text);
            return this;
        }

        public UndoStringBuilder insert(int offset, String text) {
            saveState();
            builder.insert(offset, text);
            return this;
        }

        public UndoStringBuilder delete(int start, int end) {
            saveState();
            builder.delete(start, end);
            return this;
        }

        public UndoStringBuilder replace(int start, int end, String text) {
            saveState();
            builder.replace(start, end, text);
            return this;
        }

        public UndoStringBuilder reverse() {
            saveState();
            builder.reverse();
            return this;
        }

        public int length() {
            return builder.length();
        }

        public void undo() {
            if (history.isEmpty()) {
                System.out.println("Отменять нечего");
                return;
            }
            String previous = history.remove(history.size() - 1);
            builder = new StringBuilder(previous);
        }

        public String toString() {
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        UndoStringBuilder text = new UndoStringBuilder("Привет");

        text.append(", мир");
        System.out.println("После append: " + text);

        text.append("!!!");
        System.out.println("После append: " + text);

        text.insert(0, ">> ");
        System.out.println("После insert: " + text);

        text.reverse();
        System.out.println("После reverse: " + text);

        text.undo();
        System.out.println("После undo: " + text);

        text.undo();
        System.out.println("После undo: " + text);

        text.undo();
        System.out.println("После undo: " + text);

        text.undo();
        System.out.println("После undo: " + text);

        text.undo();
    }
}
