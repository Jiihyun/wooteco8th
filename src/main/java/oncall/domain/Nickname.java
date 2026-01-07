package oncall.domain;

import java.util.Objects;
import oncall.exception.ExceptionMessage;

public class Nickname {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 5;

    private final String value;

    public Nickname(String value) {
        validateLength(value);
        this.value = value;
    }

    private void validateLength(String value) {
        if (isOutOfRange(value.length())) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NICKNAME.getMessage());
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

        return Objects.equals(getValue(), nickname.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
