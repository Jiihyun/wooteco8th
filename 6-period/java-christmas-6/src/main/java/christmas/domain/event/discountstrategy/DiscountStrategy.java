package christmas.domain.event.discountstrategy;

import christmas.domain.Orders;
import christmas.domain.VisitDate;

public interface DiscountStrategy {

    boolean canApply(VisitDate visitDate);

    int calculateDiscountAmount(VisitDate visitDate, Orders orders);

    String getName();
}
