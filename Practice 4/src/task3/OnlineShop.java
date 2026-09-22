package task3;

import java.util.Scanner;

public class OnlineShop {

    private static final String LOGIN = "user";
    private static final String PASSWORD = "1234";

    private Product[] products;
    private Product[] cart;
    private int cartCount;

    public OnlineShop() {
        products = new Product[]{
                new Product("iPhone 15", 89990, Catalog.ТЕЛЕФОНЫ),
                new Product("Samsung S24", 74500, Catalog.ТЕЛЕФОНЫ),
                new Product("Lenovo IdeaPad", 55000, Catalog.НОУТБУКИ),
                new Product("MacBook Air", 124000, Catalog.НОУТБУКИ),
                new Product("Sony WH-1000", 27900, Catalog.НАУШНИКИ),
                new Product("JBL Tune", 4990, Catalog.НАУШНИКИ)
        };
        cart = new Product[20];
        cartCount = 0;
    }

    public boolean login(Scanner scanner) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("Логин: ");
            String login = scanner.nextLine();
            System.out.print("Пароль: ");
            String password = scanner.nextLine();

            if (login.equals(LOGIN) && password.equals(PASSWORD)) {
                System.out.println("Вход выполнен");
                return true;
            }
            System.out.println("Неверный логин или пароль");
        }
        return false;
    }

    public void printCatalogs() {
        System.out.println("Каталоги товаров:");
        Catalog[] catalogs = Catalog.values();
        for (int i = 0; i < catalogs.length; i++) {
            System.out.println((i + 1) + ") " + catalogs[i].getTitle());
        }
    }

    public void printProducts(Catalog catalog) {
        System.out.println("Товары каталога \"" + catalog.getTitle() + "\":");
        int number = 1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getCatalog() == catalog) {
                System.out.println(number + ") " + products[i]);
                number++;
            }
        }
    }

    public Product findInCatalog(Catalog catalog, int number) {
        int current = 1;
        for (int i = 0; i < products.length; i++) {
            if (products[i].getCatalog() == catalog) {
                if (current == number) {
                    return products[i];
                }
                current++;
            }
        }
        return null;
    }

    public void addToCart(Product product) {
        if (cartCount < cart.length) {
            cart[cartCount] = product;
            cartCount++;
            System.out.println("Товар \"" + product.getName() + "\" добавлен в корзину");
        }
    }

    public void buy() {
        if (cartCount == 0) {
            System.out.println("Корзина пуста");
            return;
        }

        System.out.println("Ваша покупка:");
        double total = 0;
        for (int i = 0; i < cartCount; i++) {
            System.out.println((i + 1) + ") " + cart[i]);
            total = total + cart[i].getPrice();
        }
        System.out.printf("Итого к оплате: %.2f руб.%n", total);
        System.out.println("Спасибо за покупку!");
        cartCount = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OnlineShop shop = new OnlineShop();

        if (!shop.login(scanner)) {
            System.out.println("Слишком много попыток входа. Выход.");
            return;
        }

        boolean work = true;
        while (work) {
            System.out.println();
            System.out.println("1 - список каталогов");
            System.out.println("2 - выбрать товар");
            System.out.println("3 - купить товары из корзины");
            System.out.println("0 - выход");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                shop.printCatalogs();
            } else if (choice.equals("2")) {
                shop.printCatalogs();
                System.out.print("Номер каталога: ");
                int catalogNumber = Integer.parseInt(scanner.nextLine());

                if (catalogNumber < 1 || catalogNumber > Catalog.values().length) {
                    System.out.println("Нет такого каталога");
                    continue;
                }

                Catalog catalog = Catalog.values()[catalogNumber - 1];
                shop.printProducts(catalog);

                System.out.print("Номер товара: ");
                int productNumber = Integer.parseInt(scanner.nextLine());

                Product product = shop.findInCatalog(catalog, productNumber);
                if (product == null) {
                    System.out.println("Нет такого товара");
                } else {
                    shop.addToCart(product);
                }
            } else if (choice.equals("3")) {
                shop.buy();
            } else if (choice.equals("0")) {
                work = false;
            } else {
                System.out.println("Нет такого пункта меню");
            }
        }

        scanner.close();
    }
}
