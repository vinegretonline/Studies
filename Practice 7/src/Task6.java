import java.util.Scanner;

public class Task6 {

    interface StringProcessable {

        int countChars(String s);

        String oddPositions(String s);

        String invert(String s);
    }

    static class ProcessStrings implements StringProcessable {

        public int countChars(String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                count++;
            }
            return count;
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
    }

    public static void main(String[] args) {
        StringProcessable processor = new ProcessStrings();

        String[] tests = {"Программирование", "abcdefg", "Java"};

        for (int i = 0; i < tests.length; i++) {
            String s = tests[i];
            System.out.println("Строка: " + s);
            System.out.println("  символов: " + processor.countChars(s));
            System.out.println("  нечётные позиции: " + processor.oddPositions(s));
            System.out.println("  перевёрнутая: " + processor.invert(s));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите свою строку: ");
        String line = scanner.nextLine();

        System.out.println("Символов: " + processor.countChars(line));
        System.out.println("Нечётные позиции: " + processor.oddPositions(line));
        System.out.println("Перевёрнутая: " + processor.invert(line));

        scanner.close();
    }
}
