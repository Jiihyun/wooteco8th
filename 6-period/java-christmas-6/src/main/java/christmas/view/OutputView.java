package christmas.view;

import christmas.domain.Order;
import java.util.List;

public final class OutputView {

    private OutputView() {
    }

    public static void showResult(int date, List<Order> orders, int totalPriceBeforeDiscount) {
        System.out.println("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!".formatted(date));
        System.out.println();
        System.out.println("<주문메뉴>");
        String orderItemFormat = "%s %d개";
        for (Order order : orders) {
            System.out.println(orderItemFormat.formatted(order.getMenuName(), order.getQuantity()));
        }
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println("%,d원".formatted(totalPriceBeforeDiscount));
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
