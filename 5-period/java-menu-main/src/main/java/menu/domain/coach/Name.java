package menu.domain.coach;

import java.util.Objects;
import menu.exception.ExceptionMessage;

public class Name {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 4;

    private final String value;

    public Name(String value) {
        validateLength(value);
        this.value = value;
    }

    private void validateLength(String value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NAME.getMessage());
        }
    }

    private boolean isOutOfRange(String value) {
        return value.length() < MIN_LENGTH || value.length() > MAX_LENGTH;
    }

    public String getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Name name)) {
            return false;
        }

        return Objects.equals(getValue(), name.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
