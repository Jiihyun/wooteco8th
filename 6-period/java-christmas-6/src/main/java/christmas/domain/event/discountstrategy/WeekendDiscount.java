package christmas.domain.event.discountstrategy;

import christmas.domain.MenuType;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements DiscountStrategy {

    private static final int DISCOUNT_AMOUNT_PER_MAIN = 2_023;

    @Override
    public boolean canApply(VisitDate visitDate) {
        LocalDate date = LocalDate.of(2023, 12, visitDate.getValue());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    @Override
    public int calculateDiscountAmount(VisitDate visitDate, Orders orders) {
        return DISCOUNT_AMOUNT_PER_MAIN * orders.calculateSpecificTypeQuantity(MenuType.MAIN);
    }

    @Override
    public String getName() {
        return "주말 할인";
    }
}
