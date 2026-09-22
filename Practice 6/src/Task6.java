public class Task6 {

    interface Printable {

        void print();
    }

    static class Note implements Printable {

        private String text;

        public Note(String text) {
            this.text = text;
        }

        public void print() {
            System.out.println("Заметка: " + text);
        }
    }

    public static void main(String[] args) {
        Printable note = new Note("Не забыть сдать практику");
        note.print();

        Printable another = new Note("Купить тетрадь");
        another.print();
    }
}
