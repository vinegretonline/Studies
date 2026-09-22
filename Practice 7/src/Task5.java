public class Task5 {

    interface StringProcessable {

        int countChars(String s);

        String oddPositions(String s);

        String invert(String s);
    }

    public static void main(String[] args) {
        System.out.println("Интерфейс StringProcessable объявляет три функции:");
        System.out.println("  countChars - подсчёт символов в строке");
        System.out.println("  oddPositions - строка из символов на нечётных позициях 1, 3, 5, ...");
        System.out.println("  invert - инвертирование строки");

        StringProcessable processor = new StringProcessable() {

            public int countChars(String s) {
                return s.length();
            }

            public String oddPositions(String s) {
                String result = "";
                for (int i = 0; i < s.length(); i = i + 2) {
                    result = result + s.charAt(i);
                }
                return result;
            }

            public String invert(String s) {
                String result = "";
                for (int i = s.length() - 1; i >= 0; i--) {
                    result = result + s.charAt(i);
                }
                return result;
            }
        };

        String s = "Программирование";
        System.out.println("Строка: " + s);
        System.out.println("Символов: " + processor.countChars(s));
        System.out.println("Нечётные позиции: " + processor.oddPositions(s));
        System.out.println("Перевёрнутая: " + processor.invert(s));
    }
}
