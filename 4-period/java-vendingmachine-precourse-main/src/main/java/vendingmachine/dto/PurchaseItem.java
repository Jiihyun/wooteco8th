package vendingmachine.dto;

public record PurchaseItem(
        String name,
        int price,
        int quantity
) {
}
