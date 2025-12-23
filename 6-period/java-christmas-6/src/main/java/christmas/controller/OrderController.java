package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.dto.OrderItemRequest;
import christmas.util.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class OrderController {

    public void run() {
        VisitDate date = RetryHandler.retryOnInvalidInput(this::createVisitDate);
        Orders orders = RetryHandler.retryOnInvalidInput(this::createOrder);
        OutputView.showResult(date.getValue(), orders.getOrders(), orders.calculateTotalPriceBeforeDiscount());
    }

    private VisitDate createVisitDate() {
        int date = InputView.readDate();
        return new VisitDate(date);
    }

    private Orders createOrder() {
        List<OrderItemRequest> requests = InputView.readOrderItems();
        List<Order> orders = requests.stream()
                .map(request -> new Order(request.name(), request.quantity()))
                .toList();
        return new Orders(orders);
    }
}
