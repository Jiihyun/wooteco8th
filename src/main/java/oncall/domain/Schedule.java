package oncall.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import oncall.exception.ExceptionMessage;

public class Schedule {

    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 35;

    private final Deque<Nickname> nicknames;

    public Schedule(List<String> nicknames) {
        validate(nicknames);
        this.nicknames = createNicknames(nicknames);
    }

    private void validate(List<String> nicknames) {
        validateUnique(nicknames);
        validateSize(nicknames);
    }

    private void validateUnique(List<String> nicknames) {
        if (isDuplicated(nicknames)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NICKNAME.getMessage());
        }
    }

    private void validateSize(List<String> nicknames) {
        if (isOutOfRange(nicknames.size())) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_SCHEDULE_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_SIZE || number > MAX_SIZE;
    }

    private boolean isDuplicated(List<String> nicknames) {
        return nicknames.stream()
                .distinct()
                .count() != nicknames.size();
    }

    private Deque<Nickname> createNicknames(List<String> nicknames) {
        return new ArrayDeque<>(nicknames.stream()
                .map(Nickname::new)
                .toList());
    }

    public int getSize() {
        return nicknames.size();
    }

    public Nickname pollFirst() {
        return nicknames.pollFirst();
    }

    public void addFirst(Nickname nickname) {
        nicknames.addFirst(nickname);
    }

    public void addLast(Nickname nickname) {
        nicknames.addLast(nickname);
    }

    public Nickname pollLast() {
        return nicknames.pollLast();
    }
}
