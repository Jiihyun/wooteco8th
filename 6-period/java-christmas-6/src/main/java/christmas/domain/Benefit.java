package christmas.domain;

import christmas.domain.event.discountstrategy.DiscountStrategy;
import java.util.LinkedHashMap;
import java.util.Map;

public class Benefit {
    private final Map<String, Integer> benefit;

    public Benefit() {
        this.benefit = new LinkedHashMap<>();
    }

    public boolean canGetFreeGift() {
`        return benefit.containsKey("증정 이벤트");
    }

    public int calculateBenefitAmount() {
        return benefit.values().stream()
                .mapToInt(value -> value)
                .sum();
    }

    public int calculatePayAmount(int totalPriceBeforeDiscount) {
        if (canGetFreeGift()) {
            return totalPriceBeforeDiscount - calculateBenefitAmount() + Menu.CHAMPAGNE.getPrice();
        }
        return totalPriceBeforeDiscount - calculateBenefitAmount();
    }

    public void put(DiscountStrategy discountStrategy, VisitDate visitDate, Orders orders) {
        if (discountStrategy.canApply(visitDate)) {
            int discountAmount = discountStrategy.calculateDiscountAmount(visitDate, orders);
            if (discountAmount > 0) {
                benefit.put(discountStrategy.getName(), discountAmount);
            }
        }
    }

    public Map<String, Integer> getBenefit() {
        return benefit;
    }
}
