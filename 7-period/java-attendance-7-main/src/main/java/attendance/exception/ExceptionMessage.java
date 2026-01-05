package attendance.exception;

public enum ExceptionMessage {

    CANNOT_READ_FILE("파일을 읽는 도중 문제가 발생했습니다."),
    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 형식입니다."),
    INVALID_RANGE("유효하지 않은 범위입니다."),
    COMMAND_NOT_FOUND("잘못된 형식을 입력하였습니다."),
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
