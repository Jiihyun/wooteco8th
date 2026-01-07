package oncall.domain;

import java.util.List;
import oncall.exception.ExceptionMessage;

public class Schedule {

    private final List<Nickname> nicknames;

    public Schedule(List<String> nicknames) {
        validateUnique(nicknames);
        this.nicknames = createNicknames(nicknames);
    }

    private void validateUnique(List<String> nicknames) {
        if (isDuplicated(nicknames)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NICKNAME.getMessage());
        }
    }

    private boolean isDuplicated(List<String> nicknames) {
        return nicknames.stream()
                .distinct()
                .count() != nicknames.size();
    }

    private List<Nickname> createNicknames(List<String> nicknames) {
        return nicknames.stream()
                .map(Nickname::new)
                .toList();
    }

    public int getSize() {
        return nicknames.size();
    }
}
