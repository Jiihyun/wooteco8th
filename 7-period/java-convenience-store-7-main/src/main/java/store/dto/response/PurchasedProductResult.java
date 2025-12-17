package store.dto.response;

import store.domain.Order;

public record PurchasedProductResult(
        String name,
        int quantity,
        int price
) {
    public static PurchasedProductResult from(Order order) {
        return new PurchasedProductResult(
                order.getProductName(),
                order.getPurchasedQuantity(),
                order.calculatePurchasedPrice()
        );
    }

    public static PurchasedProductResult of(Order order, int getQuantity) {
        int finalQuantity = order.getPurchasedQuantity() + getQuantity;
        int finalPrice = order.getProductPrice() * finalQuantity;
        return new PurchasedProductResult(
                order.getProductName(),
                finalQuantity,
                finalPrice
        );
    }
}
