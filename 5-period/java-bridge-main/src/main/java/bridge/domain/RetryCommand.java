package bridge.domain;

import bridge.exception.ExceptionMessage;
import java.util.Arrays;

public enum RetryCommand {

    R("재시도"),
    Q("종료");

    private final String description;

    RetryCommand(final String description) {
        this.description = description;
    }

    public static RetryCommand from(String input) {
        return Arrays.stream(RetryCommand.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.RETRY_COMMAND_NOT_EXISTS.getMessage()));
    }
}
