package christmas.view;

import christmas.domain.Order;
import christmas.domain.event.Badge;
import christmas.domain.event.EventProcessor;
import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void showResult(int date, List<Order> orders,
                                  int totalPriceBeforeDiscount, EventProcessor eventProcessor) {
        System.out.println("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!".formatted(date));
        showOrderItem(orders);
        showTotalPriceBeforeDiscount(totalPriceBeforeDiscount, eventProcessor);
        showBenefit(eventProcessor);
        showBenefitAmount(eventProcessor);
        showPayAmount(totalPriceBeforeDiscount, eventProcessor);
        showBadge(eventProcessor);
    }

    private static void showOrderItem(List<Order> orders) {
        System.out.println(NEW_LINE + "<주문 메뉴>");
        String orderItemFormat = "%s %d개";
        for (Order order : orders) {
            System.out.println(orderItemFormat.formatted(order.getMenuName(), order.getQuantity()));
        }
    }

    private static void showTotalPriceBeforeDiscount(int totalPriceBeforeDiscount, EventProcessor eventProcessor) {
        System.out.println(NEW_LINE + "<할인 전 총주문 금액>");
        System.out.println("%,d원".formatted(totalPriceBeforeDiscount));
        showFreeGift(eventProcessor);
    }

    private static void showFreeGift(EventProcessor eventProcessor) {
        System.out.println(NEW_LINE + "<증정 메뉴>");
        if (eventProcessor.canGetFreeGift()) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    private static void showBenefit(EventProcessor eventProcessor) {
        System.out.println(NEW_LINE + "<혜택 내역>");
        String format = "%s: -%,d원";
        if (eventProcessor.getBenefit().isEmpty()) {
            System.out.println("없음");
        }
        for (Map.Entry<String, Integer> entry : eventProcessor.getBenefit().entrySet()) {
            System.out.println(format.formatted(entry.getKey(), entry.getValue()));
        }
    }

    private static void showBenefitAmount(EventProcessor eventProcessor) {
        System.out.println(NEW_LINE + "<총혜택 금액>");
        System.out.println("-%,d원".formatted(eventProcessor.calculateBenefitAmount()));
    }

    private static void showPayAmount(int totalPriceBeforeDiscount, EventProcessor eventProcessor) {
        System.out.println(NEW_LINE + "<할인 후 예상 결제 금액>");
        System.out.println("%,d원".formatted(eventProcessor.calculatePayAmount(totalPriceBeforeDiscount)));
    }

    private static void showBadge(EventProcessor eventProcessor) {
        System.out.println(NEW_LINE + "<12월 이벤트 배지>");
        System.out.println(Badge.from(eventProcessor.calculateBenefitAmount()).getName());
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
