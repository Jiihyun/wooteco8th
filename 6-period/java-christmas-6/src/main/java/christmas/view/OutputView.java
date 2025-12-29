package christmas.view;

import christmas.domain.event.Badge;
import christmas.dto.BenefitResult;
import christmas.dto.OrderItemResult;
import christmas.dto.OrderResult;
import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void showResult(OrderResult orderResult, BenefitResult benefitResult) {
        System.out.println("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!".formatted(orderResult.visitDate()));
        showOrderItem(orderResult.orderItemResults());
        showTotalPriceBeforeDiscount(orderResult.totalPriceBeforeDiscount());
        showFreeGift(orderResult.canGetFreeGift());
        showBenefit(benefitResult.benefit());
        showBenefitAmount(benefitResult.benefitAmount());
        showPayAmount(benefitResult.payAmountAfterDiscount());
        showBadge(benefitResult.benefitAmount());
    }

    private static void showOrderItem(List<OrderItemResult> orderItems) {
        System.out.println(NEW_LINE + "<주문 메뉴>");
        String orderItemFormat = "%s %d개";
        for (OrderItemResult orderItemResult : orderItems) {
            System.out.println(orderItemFormat.formatted(orderItemResult.name(), orderItemResult.quantity()));
        }
    }

    private static void showTotalPriceBeforeDiscount(int totalPriceBeforeDiscount) {
        System.out.println(NEW_LINE + "<할인 전 총주문 금액>");
        System.out.println("%,d원".formatted(totalPriceBeforeDiscount));
    }

    private static void showFreeGift(boolean canGetFreeGift) {
        System.out.println(NEW_LINE + "<증정 메뉴>");
        if (canGetFreeGift) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    private static void showBenefit(Map<String, Integer> benefit) {
        System.out.println(NEW_LINE + "<혜택 내역>");
        String format = "%s: -%,d원";
        if (benefit.isEmpty()) {
            System.out.println("없음");
        }
        for (Map.Entry<String, Integer> entry : benefit.entrySet()) {
            System.out.println(format.formatted(entry.getKey(), entry.getValue()));
        }
    }

    private static void showBenefitAmount(int benefitAmount) {
        System.out.println(NEW_LINE + "<총혜택 금액>");
        System.out.println("-%,d원".formatted(benefitAmount));
    }

    private static void showPayAmount(int payAmountAfterDiscount) {
        System.out.println(NEW_LINE + "<할인 후 예상 결제 금액>");
        System.out.println("%,d원".formatted(payAmountAfterDiscount));
    }

    private static void showBadge(int benefitAmount) {
        System.out.println(NEW_LINE + "<12월 이벤트 배지>");
        System.out.println(Badge.from(benefitAmount).getName());
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
