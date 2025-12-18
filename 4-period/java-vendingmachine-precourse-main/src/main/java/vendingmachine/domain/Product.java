package vendingmachine.domain;

import java.util.Objects;
import vendingmachine.exception.ExceptionMessage;

public class Product {

    private static final int MIN_PRICE = 100;

    private final String name;
    private final int price;

    public Product(String name, int price) {
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PRODUCT_PRICE_RANGE.getMessage());
        }
        if (price % Coin.COIN_10.getAmount() != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PRODUCT_PRICE.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Product product)) {
            return false;
        }

        return Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
