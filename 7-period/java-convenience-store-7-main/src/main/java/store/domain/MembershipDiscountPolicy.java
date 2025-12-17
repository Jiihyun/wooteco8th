package store.domain;

public class MembershipDiscountPolicy {

    private static final double DISCOUNT_RATE = 0.3;
    private static final int MAX_DISCOUNT = 8_000;

    public int discount(boolean useMembership, int nonPromotionAmount) {
        if (useMembership) {
            int discounted = (int) (nonPromotionAmount * DISCOUNT_RATE);
            return Math.min(discounted, MAX_DISCOUNT);
        }
        return 0;
    }
}
