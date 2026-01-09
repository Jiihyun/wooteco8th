package lotto.domain;

import lotto.exception.ExceptionMessage;

public class LottoNumber {

    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_RANGE || number > MAX_RANGE;
    }

    public int getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) {
            return false;
        }

        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return getValue();
    }
}
