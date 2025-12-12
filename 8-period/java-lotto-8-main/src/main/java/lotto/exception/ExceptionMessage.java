package lotto.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_NUMBER("유효하지 않은 숫자입니다."),

    INVALID_FORMAT("유효하지 않은 포맷입니다."),
    ;
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
