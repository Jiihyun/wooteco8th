package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum AnswerCommand {

    YES("네"),
    NO("아니오"),
    ;

    private final String description;

    AnswerCommand(String description) {
        this.description = description;
    }

    public static AnswerCommand from(String command) {
        return Arrays.stream(AnswerCommand.values())
                .filter(element -> element.description.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }

    public boolean isYes() {
        return this == YES;
    }

    public boolean isNo() {
        return this == NO;
    }
}
