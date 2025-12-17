package store.controller;

import java.util.ArrayList;
import java.util.List;
import store.domain.AnswerCommand;
import store.domain.MembershipDiscountPolicy;
import store.domain.Order;
import store.domain.Products;
import store.domain.PromotionProcessor;
import store.dto.request.OrderDecisionRequest;
import store.dto.request.OrderRequest;
import store.dto.response.OrderProcessResult;
import store.dto.response.PurchasedProductResult;
import store.dto.response.SingleOrderResult;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {

    public void run(Products products) {
        do {
            List<Order> orders = makeOrder(products);
            OrderProcessResult orderProcessResult = processOrder(orders);
            int membershipDiscount = processMembershipDiscount(orderProcessResult.sumOfNonPromotionAmount());
            OutputView.showReceipt(orderProcessResult, membershipDiscount);
        } while (wantAdditionalOrder());
    }

    private List<Order> makeOrder(Products products) {
        OutputView.showInventory(products.findAll());
        return RetryHandler.retryOnInvalidInput(() -> createOrders(products));
    }

    private List<Order> createOrders(Products products) {
        List<Order> orders = new ArrayList<>();
        List<OrderRequest> requests = InputView.readProductAndQuantity();
        for (OrderRequest request : requests) {
            Order order = new Order(request.productName(), request.quantity(), products);
            orders.add(order);
        }
        return orders;
    }

    private OrderProcessResult processOrder(List<Order> orders) {
        PromotionProcessor promotionProcessor = new PromotionProcessor();
        List<SingleOrderResult> singleOrderResults = new ArrayList<>();
        for (Order order : orders) {
            singleOrderResults.add(processSingleOrder(order, promotionProcessor));
        }
        return OrderProcessResult.from(singleOrderResults);
    }

    private SingleOrderResult processSingleOrder(Order order, PromotionProcessor promotionProcessor) {
        if (order.hasInactivePromotion()) {
            return processInactivePromotion(order);
        }
        OrderDecisionRequest request = askUserDecisions(order);
        return promotionProcessor.process(request, order);
    }

    private SingleOrderResult processInactivePromotion(Order order) {
        PurchasedProductResult purchasedProductResult = PurchasedProductResult.from(order);
        int sumOfNonPromotionAmount = order.calculatePurchasedPrice();
        order.processNonPromotionOrder();
        return SingleOrderResult.createWithoutFreeProduct(purchasedProductResult, sumOfNonPromotionAmount);
    }

    private OrderDecisionRequest askUserDecisions(Order order) {
        boolean acceptNonPromotionPrice = askAcceptNonPromotionPrice(order);
        boolean wantExtraFreeProduct = askExtraFreeProduct(order);
        return new OrderDecisionRequest(acceptNonPromotionPrice, wantExtraFreeProduct);
    }

    private boolean askAcceptNonPromotionPrice(Order order) {
        if (order.hasSufficientPromotionQuantity()) {
            return false;
        }
        int insufficientQuantity = order.getInsufficientQuantity();
        AnswerCommand answer = RetryHandler.retryOnInvalidInput(
                () -> InputView.readAnswerOfFullPrice(order.getProductName(), insufficientQuantity));
        return answer.isYes();
    }

    private boolean askExtraFreeProduct(Order order) {
        if (order.canGetFreeProduct(order.getPromotion())) {
            AnswerCommand answer = RetryHandler.retryOnInvalidInput(
                    () -> InputView.readAnswerOfFreeProduct(order.getProductName()));
            return answer.isYes();
        }
        return false;
    }

    private int processMembershipDiscount(int sumOfNonPromotionAmounts) {
        MembershipDiscountPolicy membershipDiscountPolicy = new MembershipDiscountPolicy();
        boolean useMembership = RetryHandler.retryOnInvalidInput(InputView::readMembership)
                .isYes();
        return membershipDiscountPolicy.discount(useMembership, sumOfNonPromotionAmounts);
    }

    private boolean wantAdditionalOrder() {
        AnswerCommand answer = RetryHandler.retryOnInvalidInput(InputView::readAdditionalPurchase);
        return answer.isYes();
    }
}
