package store.dto.request;

public record OrderDecisionRequest(
        boolean acceptNonPromotionPrice,
        boolean wantExtraFreeProduct
) {
}
