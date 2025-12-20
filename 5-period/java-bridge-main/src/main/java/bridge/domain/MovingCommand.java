package bridge.domain;

import bridge.exception.ExceptionMessage;
import java.util.Arrays;

public enum MovingCommand {

    U("위"),
    D("아래");

    private final String description;

    MovingCommand(final String description) {
        this.description = description;
    }

    public static MovingCommand from(String input) {
        return Arrays.stream(MovingCommand.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MOVING_COMMAND_NOT_EXISTS.getMessage()));
    }
}
