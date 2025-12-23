package christmas.dto;

import java.util.List;

public record OrderResult(
        int visitDate,
        List<OrderItemResult> orderItemResults,
        boolean canGetFreeGift,
        int totalPriceBeforeDiscount
) {
}
