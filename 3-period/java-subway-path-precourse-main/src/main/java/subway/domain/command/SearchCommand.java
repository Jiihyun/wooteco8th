package subway.domain.command;

import java.util.Arrays;
import subway.exception.ExceptionMessage;

public enum SearchCommand {

    최단_거리("최단 거리", "1"),
    최소_시간("최소 시간", "2"),
    BACK("돌아가기", "B"),
    ;

    private final String description;
    private final String command;

    SearchCommand(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public static SearchCommand from(String input) {
        return Arrays.stream(SearchCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }

    public String getCommand() {
        return command;
    }
}
