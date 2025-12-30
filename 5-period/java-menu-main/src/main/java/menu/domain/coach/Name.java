package menu.domain.coach;

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
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER.getMessage());
        }
    }

    private boolean isOutOfRange(String value) {
        return value.length() < MIN_LENGTH || value.length() > MAX_LENGTH;
    }
}
