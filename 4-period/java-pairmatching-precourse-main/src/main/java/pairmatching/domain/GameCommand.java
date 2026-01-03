package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum GameCommand {

    MATCHING("페어 매칭", "1"),
    FIND("페어 조회", "2"),
    RESET("페어 초기화", "3"),
    QUIT("종료", "Q"),
    ;

    private final String description;
    private final String command;

    GameCommand(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(GameCommand.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }

    public boolean isMatching() {
        return this == MATCHING;
    }

    public boolean isFind() {
        return this == FIND;
    }

    public boolean isReset() {
        return this == RESET;
    }
}
