import java.util.Comparator;

public enum Comparator1 {
    BY_PRICE(Comparator.comparing(Product::getPrice)),
    BY_QUANTITY(Comparator.comparing(Product::getQuantity)),
    BY_RATING(Comparator.comparing(Product::getRating)),
    BY_TITLE(Comparator.comparing(Product::getTitle)),
    ;
    private final Comparator<Product>comparator;

    Comparator1(Comparator<Product> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Product> getComparator() {
        return comparator;
    }
}
