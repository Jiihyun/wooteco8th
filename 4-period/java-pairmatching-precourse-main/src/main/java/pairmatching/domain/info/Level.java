package pairmatching.domain.info;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum Level {

    레벨1("레벨1"),
    레벨2("레벨2"),
    레벨3("레벨3"),
    레벨4("레벨4"),
    레벨5("레벨5"),
    ;

    private final String description;

    Level(String description) {
        this.description = description;
    }

    public static Level from(String input) {
        return Arrays.stream(Level.values())
                .filter(element -> element.description.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.LEVEL_NOT_FOUND.getMessage()));
    }
}
