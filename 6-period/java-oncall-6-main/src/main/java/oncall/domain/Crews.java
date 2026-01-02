package oncall.domain;

import java.util.List;
import oncall.exception.ExceptionMessage;

public class Crews {

    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 35;

    private final List<Nickname> crews;

    public Crews(List<Nickname> crews) {
        validateDuplicate(crews);
        validateRange(crews.size());
        this.crews = crews;
    }

    public void validateDuplicate(List<Nickname> values) {
        if (isDuplicated(values)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NICKNAME.getMessage());
        }
    }

    private boolean isDuplicated(List<Nickname> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    private void validateRange(int size) {
        if (isOutOfRange(size)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_CREW_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_SIZE || number > MAX_SIZE;
    }
}
