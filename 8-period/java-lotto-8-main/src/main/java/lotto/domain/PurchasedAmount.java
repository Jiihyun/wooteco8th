package lotto.domain;

import lotto.exception.ExceptionMessage;

public class PurchasedAmount {

    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 100_000;

    private final int value;

    public PurchasedAmount(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int purchasedAmount) {
        validateUnit(purchasedAmount);
        validateRange(purchasedAmount);
    }

    private void validateUnit(int purchasedAmount) {
        if (purchasedAmount % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT.getMessage());
        }
    }

    private void validateRange(int purchasedAmount) {
        if (isOutOfRange(purchasedAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT.getMessage());
        }
    }

    private boolean isOutOfRange(int purchasedAmount) {
        return purchasedAmount < MIN_AMOUNT || purchasedAmount > MAX_AMOUNT;
    }

    public int getValue() {
        return value;
    }
}
