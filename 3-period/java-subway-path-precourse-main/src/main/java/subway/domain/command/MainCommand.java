package subway.domain.command;

import java.util.Arrays;
import subway.exception.ExceptionMessage;

public enum MainCommand {

    SEARCH("경로 조회", "1"),
    QUIT("종료", "Q"),
    ;

    private final String description;
    private final String command;

    MainCommand(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public static MainCommand from(String input) {
        return Arrays.stream(MainCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }
}
