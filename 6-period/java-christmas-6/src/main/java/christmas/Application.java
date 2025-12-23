package christmas;

import christmas.dto.OrderItemRequest;
import christmas.view.InputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        int date = InputView.readDate();
        List<OrderItemRequest> requests = InputView.readOrderItems();
    }
}
