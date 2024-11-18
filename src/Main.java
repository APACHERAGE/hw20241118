import java.util.*;
/*
1. Давайте еще раз реализуем следующую задачу: ДЗ

но, на этот раз вам необходимо

добавьте в товар поле (enum) отвечающее за единицы измерения товара (шт., кг, литры, упаковки)
создать enum отвечающий за способы сортировки продуктов (подсказка: компаратор может быть частью enum)
реализовать тесты (используйте параметризованные тесты, где это возможно)
программа должна быть устойчива к некорректному вводу со стороны пользователя
(по желанию) Попробуйте реализовать процесс добавления товара в корзину и покупки. Т.е. вам неоьходмо создать класс "корзина покупателя". Очевидно в нем должна быть какая-то структура данных, которая позволит хранить список покупок. Реализуйте для пользователя возможность добавление товара в карзину (кстати, не забудьте, что нельзя добавить в карзину больше товара, чем есть на складе)
 */
public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
                new Product("banana", 1.4, 8.3, 100, Metrics.KILOS),
                new Product("orange", 1.8, 7.5, 80, Metrics.KILOS),
                new Product("milk", 0.99, 8.7, 30, Metrics.LITERS),
                new Product("apple", 2.2, 9.1, 70, Metrics.KILOS),
                new Product("pineapple", 3.4, 6.1, 70, Metrics.PIECES)
        ));

        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Показать список товаров");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("3. Показать корзину");
            System.out.println("4. Показать сортированый список товраров");
            System.out.println("5. Оформить покупку");
            System.out.println("6. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> printProducts(products);
                case 2 -> addProductToCart(products, cart, scanner);
                case 3 -> cart.printCart();
                case 4 -> sortProducts(products, scanner);
                case 5 -> {
                    System.out.println("Оформление покупки...");
                    System.out.println("Итоговая сумма: " + cart.calculateTotal() + " Eur.");
                    return;
                }
                case 6 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    public static void addProductToCart(List<Product> products, Cart cart, Scanner scanner) {
        System.out.println("\nВведите номер товара, который хотите добавить в корзину:");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, products.get(i));
        }

        int productIndex = scanner.nextInt() - 1;
        if (productIndex < 0 || productIndex >= products.size()) {
            System.out.println("Нправельный номер товара. ВВедите еще раз.");
            return;
        }

        Product selectedProduct = products.get(productIndex);
        System.out.printf("Сколько %s хотите добавить в корзину? ", selectedProduct.getM());
        double quantity = scanner.nextDouble();

        try {
            cart.addToCart(selectedProduct, quantity);
            System.out.printf("%.2f %s %s добавлено в корзину.%n",
                    quantity, selectedProduct.getM(), selectedProduct.getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void sortProducts(List<Product> products, Scanner scanner) {
        Comparator<Product> comparator = selectSortType(scanner);
        if (comparator != null) {
            products.sort(comparator);
            System.out.println("Список товаров отсортирован:");
            printProducts(products);
        } else {
            System.out.println("Сортировка не вышла");
        }
    }

    public static Comparator<Product> selectSortType(Scanner scanner) {
        System.out.println("Введите номер сортировки списка: ");
        System.out.println("1. по названию ");
        System.out.println("2. по цене ");
        System.out.println("3. по рейтингу ");
        System.out.println("4. по количеству ");
        System.out.println("5. по названию (в обратном порядке)");
        System.out.println("6. по цене (в обратном порядке)");
        System.out.println("7. по рейтингу (в обратном порядке)");
        System.out.println("8. по количеству (в обратном порядке)");

        System.out.println("любая другая цифра - выход");
        int select = scanner.nextInt();

        return switch (select) {
            case 1 -> Comparator1.BY_TITLE.getComparator();
            case 2 -> Comparator1.BY_PRICE.getComparator();
            case 3 -> Comparator1.BY_RATING.getComparator();
            case 4 -> Comparator1.BY_QUANTITY.getComparator();
            case 5 -> Comparator1.BY_TITLE.getComparator().reversed();
            case 6 -> Comparator1.BY_PRICE.getComparator().reversed();
            case 7 -> Comparator1.BY_RATING.getComparator().reversed();
            case 8 -> Comparator1.BY_QUANTITY.getComparator().reversed();
            default -> null;
        };
    }

    public static void printProducts(List<Product> products) {
        System.out.println("\nСписок товаров:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
