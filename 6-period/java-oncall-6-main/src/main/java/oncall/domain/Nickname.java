package oncall.domain;

import java.util.Objects;
import oncall.exception.ExceptionMessage;

public class Nickname {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 5;

    private final String value;

    public Nickname(String value) {
        validateLength(value.length());
        this.value = value;
    }

    private void validateLength(int length) {
        if (isOutOfRange(length)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_LENGTH_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_LENGTH || number > MAX_LENGTH;
    }

    public String getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Nickname nickname)) {
            return false;
        }

        return Objects.equals(value, nickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
