package menu.domain.coach;

import java.util.List;
import menu.exception.ExceptionMessage;

public class Names {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 5;

    private final List<Name> names;

    public Names(List<String> names) {
        validateUniqueName(names);
        validateRange(names.size());
        this.names = names.stream()
                .map(Name::new)
                .toList();
    }

    private void validateUniqueName(List<String> names) {
        if (isDuplicated(names)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NAME.getMessage());
        }
    }

    private boolean isDuplicated(List<String> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    private void validateRange(int size) {
        if (isOutOfRange(size)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_COACH_SIZE.getMessage());
        }
    }

    private boolean isOutOfRange(int size) {
        return size < MIN_SIZE || size > MAX_SIZE;
    }

    public List<Name> getNames() {
        return names;
    }
}
