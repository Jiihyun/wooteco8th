package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.EventProcessor;
import christmas.dto.BenefitResult;
import christmas.dto.OrderItemRequest;
import christmas.dto.OrderItemResult;
import christmas.dto.OrderResult;
import christmas.util.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class OrderController {

    public void run() {
        VisitDate date = RetryHandler.retryOnInvalidInput(this::createVisitDate);
        Orders orders = RetryHandler.retryOnInvalidInput(this::createOrder);

        Benefit benefit = calculateBenefit(date, orders);
        int totalPriceBeforeDiscount = orders.calculateTotalPriceBeforeDiscount();
        OrderResult orderResult = createOrderResult(benefit, orders, date, totalPriceBeforeDiscount);
        BenefitResult benefitResult = new BenefitResult(benefit.getBenefit(),
                benefit.calculateBenefitAmount(), benefit.calculatePayAmount(totalPriceBeforeDiscount));
        OutputView.showResult(orderResult, benefitResult);
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

    private Benefit calculateBenefit(VisitDate date, Orders orders) {
        EventProcessor eventProcessor = new EventProcessor();
        return eventProcessor.calculateBenefit(date, orders);
    }

    private OrderResult createOrderResult(Benefit benefit, Orders orders, VisitDate date, int totalPriceBeforeDiscount) {
        boolean canGetFreeGift = benefit.canGetFreeGift();
        List<OrderItemResult> orderItemResults = orders.getOrders().stream()
                .map(order -> new OrderItemResult(order.getMenuName(), order.getQuantity()))
                .toList();

        return new OrderResult(date.getDayOfMonth(), orderItemResults,
                canGetFreeGift, totalPriceBeforeDiscount);
    }
}
