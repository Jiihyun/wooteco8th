package store.domain;

import store.exception.ExceptionMessage;

public class Inventory {

    private int promotionQuantity;
    private int nonPromotionQuantity;

    public Inventory(int promotionQuantity, int nonPromotionQuantity) {
        this.promotionQuantity = promotionQuantity;
        this.nonPromotionQuantity = nonPromotionQuantity;
    }

    public void addPromotionQuantity(int quantity) {
        this.promotionQuantity = quantity;
    }

    public void addNonPromotionQuantity(int quantity) {
        this.nonPromotionQuantity = quantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getNonPromotionQuantity() {
        return nonPromotionQuantity;
    }

    public void minusPromotionQuantity(int purchasedQuantity) {
        if (hasInsufficientPromotionQuantity(purchasedQuantity)) {
            int purchasedQuantityAfterMinusPromotion = purchasedQuantity - promotionQuantity;
            validateQuantity(nonPromotionQuantity, purchasedQuantityAfterMinusPromotion);
            this.nonPromotionQuantity -= purchasedQuantityAfterMinusPromotion;
            this.promotionQuantity = 0;
            return;
        }
        validateQuantity(promotionQuantity, purchasedQuantity);
        this.promotionQuantity -= purchasedQuantity;
    }

    public boolean hasSufficientPromotionQuantity(int purchasedQuantity) {
        return promotionQuantity >= purchasedQuantity;
    }

    public boolean hasInsufficientPromotionQuantity(int purchasedQuantity) {
        return promotionQuantity < purchasedQuantity;
    }

    public void minusNonPromotionQuantity(int purchasedQuantity) {
        validateQuantity(nonPromotionQuantity, purchasedQuantity);
        this.nonPromotionQuantity -= purchasedQuantity;
    }

    private void validateQuantity(int savedQuantity, int purchasedQuantity) {
        if (savedQuantity < purchasedQuantity) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_STOCK.getMessage());
        }
    }
}
