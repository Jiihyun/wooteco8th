package vendingmachine.domain;

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
}
