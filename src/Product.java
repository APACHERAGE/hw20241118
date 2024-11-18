import java.math.BigDecimal;

public class Product {
    private String title;
    private double price;
    private double rating;
    private int quantity;
    private Metrics m;


    public Metrics getM() {
        return m;
    }

    public Product(String title, double price, double rating, int quantity, Metrics m) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Wrong quatity");
        }
        if (m == null) {
            throw new IllegalArgumentException("Wrong metrics");
        }
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity;
        this.m = m;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", quantity=" + quantity +
                '}';
    }

    public void reduceQuantity(double amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException("Недостаточно товара на складе");
        }
        quantity -= amount;
    }
}