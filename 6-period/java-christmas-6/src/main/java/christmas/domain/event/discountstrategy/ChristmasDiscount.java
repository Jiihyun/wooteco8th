package christmas.domain.event.discountstrategy;

import christmas.domain.Orders;
import christmas.domain.VisitDate;

public class ChristmasDiscount implements DiscountStrategy {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    private static final int INCREASE_DISCOUNT_AMOUNT = 100;

    @Override
    public boolean canApply(VisitDate visitDate) {
        return visitDate.isRightRange(MIN_DATE, MAX_DATE);
    }

    @Override
    public int calculateDiscountAmount(VisitDate visitDate, Orders orders) {
        return DEFAULT_DISCOUNT_AMOUNT + ((visitDate.getDayOfMonth() - 1) * INCREASE_DISCOUNT_AMOUNT);
    }

    @Override
    public String getName() {
        return "크리스마스 디데이 할인";
    }
}
