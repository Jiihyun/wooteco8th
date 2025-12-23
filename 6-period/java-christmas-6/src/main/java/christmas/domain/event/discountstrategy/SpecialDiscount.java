package christmas.domain.event.discountstrategy;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount implements DiscountStrategy {

    private static final int DISCOUNT_AMOUNT = 1_000;
    private static final int DATE = 25;

    @Override
    public boolean canApply(VisitDate visitDate) {
        LocalDate date = LocalDate.of(2023, 12, visitDate.getValue());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || visitDate.isRightRange(DATE, DATE);
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
