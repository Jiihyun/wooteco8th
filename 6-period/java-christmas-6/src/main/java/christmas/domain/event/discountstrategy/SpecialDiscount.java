package christmas.domain.event.discountstrategy;

import christmas.domain.Orders;
import christmas.domain.VisitDate;

public class SpecialDiscount implements DiscountStrategy {

    private static final int DISCOUNT_AMOUNT = 1_000;
    private static final int DATE = 25;

    @Override
    public boolean canApply(VisitDate visitDate) {
        return visitDate.isSpecialDay();
    }

    @Override
    public int calculateDiscountAmount(VisitDate visitDate, Orders orders) {
        return DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return "특별 할인";
    }
}
