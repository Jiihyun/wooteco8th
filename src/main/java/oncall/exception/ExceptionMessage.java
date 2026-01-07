package oncall.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_NICKNAME("유효하지 않은 닉네임 형식입니다."),
    DAYOFWEEK_NOT_FOUND("존재하지 않는 요일입니다."),
    MONTH_NOT_FOUND("존재하지 않는 달입니다."),
    DUPLICATED_NICKNAME("중복된 닉네임입니다."),
    INVALID_SCHEDULE_SIZE("평일, 휴일 근무 인원이 다릅니다."),
    INVALID_SCHEDULE_RANGE("근무 인원이 올바르지 않습니다."),
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
