package christmas.domain;

import christmas.exception.ExceptionMessage;

public class Order {

    private static final int MIN_QUANTITY = 1;
    public static final int MAX_QUANTITY = 20;

    private final Menu menu;
    private final int quantity;

    public Order(String name, int quantity) {
        validateQuantity(quantity);
        this.menu = Menu.from(name);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (isOutOfRange(quantity)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_QUANTITY_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MIN_QUANTITY || number > MAX_QUANTITY;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isDrink() {
        return menu.isDrink();
    }
}
