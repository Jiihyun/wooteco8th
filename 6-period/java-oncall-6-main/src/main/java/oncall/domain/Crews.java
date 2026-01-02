package oncall.domain;

import java.util.List;
import oncall.exception.ExceptionMessage;

public class Crews {

    private final List<Nickname> crews;

    public Crews(List<Nickname> crews) {
        validateDuplicate(crews);
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
}
