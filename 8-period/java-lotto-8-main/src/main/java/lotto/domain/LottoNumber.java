package lotto.domain;

import lotto.exception.ExceptionMessage;

public class LottoNumber {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_RANGE = 45;

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException(ExceptionMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MIN_NUMBER || number > MAX_RANGE;
    }

    public int getValue() {
        return value;
    }
}
