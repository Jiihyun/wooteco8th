package lotto.domain;

import lotto.exception.ExceptionMessage;

public class PurchasedAmount {

    public static final int LOTTO_PRICE = 1_000;
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
        if (purchasedAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT.getMessage());
        }
    }

    private void validateRange(int purchasedAmount) {
        if (isOutOfRange(purchasedAmount)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT.getMessage());
        }
    }

    private boolean isOutOfRange(int purchasedAmount) {
        return purchasedAmount < LOTTO_PRICE || purchasedAmount > MAX_AMOUNT;
    }

    public int calculateLottoQuantity() {
        return value / LOTTO_PRICE;
    }

    public int getValue() {
        return value;
    }
}
