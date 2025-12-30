package menu.domain.coach;

import java.util.List;
import menu.exception.ExceptionMessage;

public class Names {

    private final List<Name> names;

    public Names(List<String> names) {
        validateUniqueName(names);
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

    public List<Name> getNames() {
        return names;
    }
}
