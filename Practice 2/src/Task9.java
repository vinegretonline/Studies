import java.util.Scanner;

public class Task9 {

    public static void main(String[] args) {
        String[] suits = {"черви", "бубны", "трефы", "пики"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "валет", "дама", "король", "туз"};

        String[] deck = new String[52];
        int index = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck[index] = ranks[j] + " " + suits[i];
                index++;
            }
        }

        for (int i = 0; i < deck.length; i++) {
            int r = (int) (Math.random() * deck.length);
            String temp = deck[i];
            deck[i] = deck[r];
            deck[r] = temp;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество игроков: ");
        int n = scanner.nextInt();

        while (n < 1 || n * 5 > 52) {
            System.out.print("Игроков должно быть от 1 до 10. Повторите ввод: ");
            n = scanner.nextInt();
        }

        int card = 0;
        for (int player = 1; player <= n; player++) {
            System.out.println("Игрок " + player + ":");
            for (int i = 0; i < 5; i++) {
                System.out.println("  " + deck[card]);
                card++;
            }
            System.out.println();
        }

        scanner.close();
    }
}
