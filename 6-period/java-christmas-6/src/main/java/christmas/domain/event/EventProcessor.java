package christmas.domain.event;

import christmas.domain.Benefit;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.discountstrategy.ChristmasDiscount;
import christmas.domain.event.discountstrategy.DiscountStrategy;
import christmas.domain.event.discountstrategy.FreeGiftDiscount;
import christmas.domain.event.discountstrategy.SpecialDiscount;
import christmas.domain.event.discountstrategy.WeekdayDiscount;
import christmas.domain.event.discountstrategy.WeekendDiscount;
import java.util.List;

public class EventProcessor {

    private static final int MIN_ORDER_AMOUNT = 10_000;

    private final List<DiscountStrategy> strategies;

    public EventProcessor() {
        this.strategies = initStrategies();
    }

    private List<DiscountStrategy> initStrategies() {
        return List.of(
                new ChristmasDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new SpecialDiscount(),
                new FreeGiftDiscount());
    }

    public Benefit calculateBenefit(VisitDate visitDate, Orders orders) {
        Benefit benefit = new Benefit();
        if (canGetBenefit(orders)) {
            strategies.forEach(strategy ->
                    benefit.put(strategy, visitDate, orders));
        }
        return benefit;
    }

    private static boolean canGetBenefit(Orders orders) {
        return orders.calculateTotalPriceBeforeDiscount() >= MIN_ORDER_AMOUNT;
    }
}
