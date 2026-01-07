package oncall.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 형식입니다."),
    INVALID_RANGE("유효하지 않은 범위입니다."),
    DAYOFWEEK_NOT_FOUND("존재하지 않는 요일입니다."),
    MONTH_NOT_FOUND("존재하지 않는 달입니다."),
    DUPLICATED("중복된 oo입니다."),
    ;

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
