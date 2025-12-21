package bridge.domain;

import bridge.exception.ExceptionMessage;
import java.util.Arrays;

public enum GameResult {

    SUCCESS("성공", true),
    FAILED("실패", false),
    ;

    private final String description;
    private final boolean isSuccess;

    GameResult(String description, boolean isSuccess) {
        this.description = description;
        this.isSuccess = isSuccess;
    }

    public static GameResult from(boolean isSuccess) {
        return Arrays.stream(GameResult.values())
                .filter(element -> element.isSuccess == isSuccess)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.GAME_RESULT_NOT_EXISTS.getMessage()));
    }

    public String getDescription() {
        return description;
    }
}
