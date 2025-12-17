package store.dto.response;

public record SingleOrderResult(
        PurchasedProductResult purchasedProduct,
        FreeProductResult freeProduct,
        int sumOfNonPromotionAmount
) {
    public static SingleOrderResult createWithoutFreeProduct(PurchasedProductResult purchasedProduct, int sumOfNonPromotionAmount) {
        return new SingleOrderResult(purchasedProduct, null, sumOfNonPromotionAmount);
    }
}
