package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum Command {

    MATCHING("페어 매칭", "1"),
    ;

    private final String description;
    private final String command;

    Command(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public static Command from(String input) {
        return Arrays.stream(Command.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }
}
