package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.dto.OrderItemRequest;
import christmas.util.RetryHandler;
import christmas.view.InputView;
import java.util.List;

public class OrderController {

    public void run() {
        int date = RetryHandler.retryOnInvalidInput(InputView::readDate);
        Orders orders = RetryHandler.retryOnInvalidInput(this::createOrder);
    }

    private Orders createOrder() {
        List<OrderItemRequest> requests = InputView.readOrderItems();
        List<Order> orders = requests.stream()
                .map(request -> new Order(request.name(), request.quantity()))
                .toList();
        return new Orders(orders);
    }
}
