package store.dto.response;

import java.util.ArrayList;
import java.util.List;

public record OrderProcessResult(
        List<PurchasedProductResult> purchasedProducts,
        List<FreeProductResult> freeProducts,
        int sumOfNonPromotionAmount
) {
    public static OrderProcessResult from(List<SingleOrderResult> singleResults) {
        List<PurchasedProductResult> purchasedProducts = new ArrayList<>();
        List<FreeProductResult> freeProducts = new ArrayList<>();
        int sumOfNonPromotionAmount = 0;

        for (SingleOrderResult result : singleResults) {
            purchasedProducts.add(result.purchasedProduct());
            if (result.freeProduct() != null) {
                freeProducts.add(result.freeProduct());
            }
            sumOfNonPromotionAmount += result.sumOfNonPromotionAmount();
        }
        return new OrderProcessResult(purchasedProducts, freeProducts, sumOfNonPromotionAmount);
    }
}
