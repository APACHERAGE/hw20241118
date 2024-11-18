import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Double> items = new HashMap<>();

    public void addToCart(Product product, double quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Недостаточно товара на складе");
        }
        items.put(product, items.getOrDefault(product, 0.0) + quantity);
        product.reduceQuantity(quantity);
    }

    public double calculateTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void printCart() {
        items.forEach((product, quantity) ->
                System.out.println(product.getTitle() + " - " + quantity + " " + product.getM())
        );
        System.out.printf("Итого: %.2f%n", calculateTotal());
    }
}
