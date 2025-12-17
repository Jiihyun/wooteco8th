package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import store.exception.ExceptionMessage;

public class Order {

    private static final int MIN_QUANTITY = 1;

    private final Product product;
    private int purchasedQuantity;
    private final LocalDate orderDate;

    public Order(String productName, int purchasedQuantity, Products products) {
        this.product = products.findByName(productName)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.PRODUCT_NOT_FOUND.getMessage()));
        validate(purchasedQuantity);
        this.purchasedQuantity = purchasedQuantity;
        this.orderDate = DateTimes.now().toLocalDate();
    }

    private void validate(int purchasedQuantity) {
        validatePurchasedQuantity(purchasedQuantity);
        validateStock(purchasedQuantity);
    }

    private void validatePurchasedQuantity(int purchasedQuantity) {
        if (isLessThanMinQuantity(purchasedQuantity)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PURCHASED_QUANTITY.getMessage());
        }
    }

    private boolean isLessThanMinQuantity(int purchasedQuantity) {
        return purchasedQuantity < MIN_QUANTITY;
    }

    private void validateStock(int purchasedQuantity) {
        if (hasInsufficientStock(purchasedQuantity)) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_STOCK.getMessage());
        }
    }

    private boolean hasInsufficientStock(int purchasedQuantity) {
        return purchasedQuantity > getProductPromotionQuantity() + product.getInventory().getNonPromotionQuantity();
    }

    public boolean hasInactivePromotion() {
        return !hasActivePromotion();
    }

    private boolean hasActivePromotion() {
        Promotion promotion = product.getPromotion();
        return promotion.isActive(orderDate);
    }

    public boolean canGetFreeProduct(Promotion promotion) {
        return promotion.canGetFreeProduct(purchasedQuantity, getProductPromotionQuantity());
    }

    public String getProductName() {
        return product.getName();
    }

    public Inventory getProductInventory() {
        return product.getInventory();
    }

    public Product getProduct() {
        return product;
    }

    public Promotion getPromotion() {
        return product.getPromotion();
    }

    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public int getProductPrice() {
        return product.getPrice();
    }

    public int getProductPromotionQuantity() {
        return product.getPromotionQuantity();
    }

    public int getInsufficientQuantity() {
        int totalQuantity = getPromotionBuyQuantity() + getPromotionGetQuantity();
        return purchasedQuantity - (totalQuantity * (getProductPromotionQuantity() / totalQuantity));
    }

    public int calculatePurchasedPrice() {
        return product.getPrice() * purchasedQuantity;
    }

    public int calculateFreeProductQuantity() {
        int promotionQuantity = Math.min(product.getPromotionQuantity(), purchasedQuantity);
        return promotionQuantity / (product.getPromotionBuyQuantity() + product.getPromotionGetQuantity());
    }

    public void processNonPromotionOrder() {
        product.minusNonPromotionQuantity(purchasedQuantity);
    }

    public boolean hasSufficientPromotionQuantity() {
        return product.hasSufficientPromotionQuantity(purchasedQuantity);
    }

    public int getPromotionBuyQuantity() {
        return product.getPromotionBuyQuantity();
    }

    public int getPromotionGetQuantity() {
        return product.getPromotionGetQuantity();
    }

    public void minusPurchasedQuantity(int insufficientQuantity) {
        this.purchasedQuantity -= insufficientQuantity;
    }
}
