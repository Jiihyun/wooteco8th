package christmas.domain;

import christmas.exception.ExceptionMessage;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validate(orders);
        this.orders = orders;
    }

    private void validate(List<Order> orders) {
        validateDuplicateMenu(orders);
        validateTotalQuantity(orders);
        validateMenuType(orders);
    }

    private void validateDuplicateMenu(List<Order> orders) {
        if (isDuplicated(orders)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_ORDER.getMessage());
        }
    }

    private boolean isDuplicated(List<Order> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    private void validateTotalQuantity(List<Order> orders) {
        int totalQuantity = calculateTotalQuantity(orders);
        if (totalQuantity > Order.MAX_QUANTITY) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_QUANTITY_RANGE.getMessage());
        }
    }

    private int calculateTotalQuantity(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }

    private void validateMenuType(List<Order> orders) {
        boolean isAllDrink = orders.stream()
                .allMatch(order -> order.isSameType(MenuType.DRINK));
        if (isAllDrink) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    public int calculateTotalPriceBeforeDiscount() {
        return orders.stream()
                .mapToInt(Order::calculateOrderPrice)
                .sum();
    }

    public int calculateSpecificTypeQuantity(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.isSameType(menuType))
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
