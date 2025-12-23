package christmas.domain.event.discountstrategy;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;

public class FreeGiftDiscount implements DiscountStrategy {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int DISCOUNT_CONDITION = 120_000;
    private static final int DISCOUNT_AMOUNT = Menu.CHAMPAGNE.getPrice();

    @Override
    public boolean canApply(VisitDate visitDate) {
        return visitDate.isRightRange(MIN_DATE, MAX_DATE);

    }

    @Override
    public int calculateDiscountAmount(VisitDate visitDate, Orders orders) {
        if (orders.calculateTotalPriceBeforeDiscount() >= DISCOUNT_CONDITION) {
            return DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    public String getName() {
        return "증정 이벤트";
    }
}
