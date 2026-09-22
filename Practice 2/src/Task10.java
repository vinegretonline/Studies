import java.util.Scanner;

public class Task10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку со словами:");
        String line = scanner.nextLine();

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != ' ') {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }

        System.out.println("Вы ввели слов: " + count);

        scanner.close();
    }
}
