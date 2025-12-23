package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.discountstrategy.ChristmasDiscount;
import christmas.domain.event.discountstrategy.DiscountStrategy;
import christmas.domain.event.discountstrategy.FreeGiftDiscount;
import christmas.domain.event.discountstrategy.SpecialDiscount;
import christmas.domain.event.discountstrategy.WeekdayDiscount;
import christmas.domain.event.discountstrategy.WeekendDiscount;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventProcessor {

    private final List<DiscountStrategy> strategies;
    private final Map<String, Integer> benefit;

    public EventProcessor() {
        this.strategies = initStrategies();
        this.benefit = new LinkedHashMap<>();
    }

    private List<DiscountStrategy> initStrategies() {
        return List.of(
                new ChristmasDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new SpecialDiscount(),
                new FreeGiftDiscount());
    }

    public boolean canGetFreeGift() {
        if (benefit.containsKey("증정 이벤트")) {
            return true;
        }
        return false;
    }

    public Map<String, Integer> calculateBenefit(VisitDate visitDate, Orders orders) {
        if (orders.calculateTotalPriceBeforeDiscount() < 10_000) {
            return benefit;
        }
        for (DiscountStrategy strategy : strategies) {
            if (!strategy.canApply(visitDate)) {
                continue;
            }
            benefit.put(strategy.getName(), strategy.calculateDiscountAmount(visitDate, orders));
        }
        return benefit;
    }

    public int calculateBenefitAmount() {
        return benefit.values().stream()
                .mapToInt(value -> value)
                .sum();
    }

    public int calculatePayAmount(int totalPriceBeforeDiscount) {
        if (benefit.containsKey("증정 이벤트")) {
            return totalPriceBeforeDiscount - calculateBenefitAmount() + Menu.CHAMPAGNE.getPrice();
        }
        return totalPriceBeforeDiscount - calculateBenefitAmount();
    }

    public Map<String, Integer> getBenefit() {
        return benefit;
    }
}
