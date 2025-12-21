package bridge.domain;

import bridge.exception.ExceptionMessage;
import java.util.Arrays;

public enum MovingCommand {

    U("위", 1),
    D("아래", 0);

    private final String description;
    private final int moveCondition;

    MovingCommand(String description, int moveCondition) {
        this.description = description;
        this.moveCondition = moveCondition;
    }

    public static MovingCommand from(String input) {
        return Arrays.stream(MovingCommand.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MOVING_COMMAND_NOT_EXISTS.getMessage()));
    }

    public static String fromCondition(int moveCondition) {
        return Arrays.stream(MovingCommand.values())
                .filter(command -> command.moveCondition == moveCondition)
                .map(Enum::name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MOVING_COMMAND_NOT_EXISTS.getMessage()));
    }
}
