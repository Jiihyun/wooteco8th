package store.domain;

import store.dto.request.OrderDecisionRequest;
import store.dto.response.FreeProductResult;
import store.dto.response.PurchasedProductResult;
import store.dto.response.SingleOrderResult;

public class PromotionProcessor {

    public SingleOrderResult process(OrderDecisionRequest request, Order order) {
        int manualFreeQuantity = calculateManualFreeQuantity(order, request);
        int autoFreeQuantity = order.calculateFreeProductQuantity();
        minusStock(request, order, manualFreeQuantity);
        return createOrderProcessResult(order, manualFreeQuantity, autoFreeQuantity);
    }

    private int calculateManualFreeQuantity(Order order, OrderDecisionRequest request) {
        if (!order.canGetFreeProduct(order.getPromotion())) {
            return 0;
        }
        if (!request.wantExtraFreeProduct()) {
            return 0;
        }
        return order.getPromotionGetQuantity();
    }

    private void minusStock(OrderDecisionRequest request, Order order, int manualFreeQuantity) {
        Inventory inventory = order.getProductInventory();
        int purchasedQuantity = order.getPurchasedQuantity();
        if (inventory.hasInsufficientPromotionQuantity(purchasedQuantity)) {
            handleInsufficientPromotion(request, order, inventory);
            return;
        }
        inventory.minusPromotionQuantity(purchasedQuantity + manualFreeQuantity);
    }

    private void handleInsufficientPromotion(OrderDecisionRequest request, Order order, Inventory inventory) {
        int purchasedQuantity = order.getPurchasedQuantity();
        int insufficientQuantity = order.getInsufficientQuantity();

        if (request.acceptNonPromotionPrice()) {
            inventory.minusPromotionQuantity(purchasedQuantity);
            return;
        }
        inventory.minusPromotionQuantity(purchasedQuantity - insufficientQuantity);
        order.minusPurchasedQuantity(insufficientQuantity);
    }

    private SingleOrderResult createOrderProcessResult(Order order, int manualFreeQuantity, int autoFreeQuantity) {
        FreeProductResult freeProductResult = createFreeProductResult(order, manualFreeQuantity, autoFreeQuantity);
        PurchasedProductResult purchasedProductResult = createPurchasedProductResult(order, freeProductResult);
        int nonPromotionQuantity = freeProductResult.totalQuantity()
                * (order.getPromotionBuyQuantity() + order.getPromotionGetQuantity());
        if (nonPromotionQuantity < order.getPurchasedQuantity()) {
            int sumOfNonPromotionAmount = (order.getPurchasedQuantity() - nonPromotionQuantity) * order.getProductPrice();
            return new SingleOrderResult(purchasedProductResult, freeProductResult, sumOfNonPromotionAmount);
        }
        return new SingleOrderResult(purchasedProductResult, freeProductResult, 0);
    }

    private FreeProductResult createFreeProductResult(Order order, int manualFreeQuantity, int autoFreeQuantity) {
        return FreeProductResult.from(order.getProduct(), autoFreeQuantity, manualFreeQuantity);
    }

    private PurchasedProductResult createPurchasedProductResult(Order order, FreeProductResult freeProductResult) {
        if (freeProductResult.freeProductQuantityByManual() > 0) {
            return PurchasedProductResult.of(order, order.getPromotionGetQuantity());
        }
        return PurchasedProductResult.from(order);
    }
}
