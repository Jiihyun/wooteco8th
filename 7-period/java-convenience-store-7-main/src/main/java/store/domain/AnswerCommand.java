package store.domain;

import java.util.Arrays;
import store.exception.ExceptionMessage;

public enum AnswerCommand {

    Y("yes"),
    N("no"),
    ;

    private final String description;

    AnswerCommand(String description) {
        this.description = description;
    }

    public static AnswerCommand from(String input) {
        return Arrays.stream(AnswerCommand.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.ANSWER_COMMAND_NOT_FOUND.getMessage()));
    }

    public boolean isYes() {
        return this == Y;
    }
}
