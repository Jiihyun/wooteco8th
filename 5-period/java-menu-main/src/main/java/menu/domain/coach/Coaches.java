package menu.domain.coach;

import java.util.List;
import menu.exception.ExceptionMessage;

public class Coaches {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 5;

    private final List<Coach> coaches;

    public Coaches(List<Coach> coaches) {
        validateRange(coaches.size());
        this.coaches = coaches;
    }

    private void validateRange(int size) {
        if (isOutOfRange(size)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_COACH_SIZE.getMessage());
        }
    }

    private boolean isOutOfRange(int size) {
        return size < MIN_SIZE || size > MAX_SIZE;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }
}
