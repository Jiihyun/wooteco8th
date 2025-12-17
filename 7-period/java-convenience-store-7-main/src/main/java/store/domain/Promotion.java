package store.domain;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final int buyQuantity;
    private final int getQuantity;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final PromotionType type;

    public enum PromotionType {
        EXISTENT, NONE
    }

    public static Promotion none() {
        return new Promotion("null", 0, 0,
                LocalDate.MIN, LocalDate.MIN, PromotionType.NONE);
    }

    public Promotion(String name, int buyQuantity, int getQuantity,
                     LocalDate startDate, LocalDate endDate, PromotionType type) {
        this.name = name;
        this.buyQuantity = buyQuantity;
        this.getQuantity = getQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public boolean hasSameName(String promotionName) {
        return this.name.equals(promotionName);
    }

    public boolean isActive(LocalDate date) {
        return exists()
                && (date.isAfter(startDate) || date.isEqual(startDate))
                && (date.isBefore(endDate) || date.isEqual(endDate));
    }

    public boolean exists() {
        return type == PromotionType.EXISTENT;

    }

    public boolean canGetFreeProduct(int purchasedQuantity, int promotionQuantity) {
        return purchasedQuantity % (buyQuantity + getQuantity) == buyQuantity
                && promotionQuantity >= purchasedQuantity;
    }

    public String getName() {
        return name;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public int getGetQuantity() {
        return getQuantity;
    }
}
