// Task 5. The program prints the command line arguments in a for loop.
// Arguments are set in Run -> Edit Configurations -> Program arguments

public class Task3 {

    public static void main(String[] args) {
        System.out.println("Количество аргументов командной строки: " + args.length);

        if (args.length == 0) {
            System.out.println("Аргументы не заданы.");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            System.out.printf("args[%d] = %s%n", i, args[i]);
        }

        System.out.println("---------------------------------");
        System.out.print("Все аргументы в строку: ");
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();
    }
}
