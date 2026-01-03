package pairmatching.exception;

public enum ExceptionMessage {

    CANNOT_READ_FILE("파일을 읽는 중 문제가 발생했습니다."),
    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 형식입니다."),
    INVALID_RANGE("유효하지 않은 범위입니다."),
    DUPLICATED("중복된 oo입니다."),
    COMMAND_NOT_FOUND("존재하지 않는 명령어 입니다."),
    COURSE_NOT_FOUND("존재하지 않는 코스 입니다."),
    LEVEL_NOT_FOUND("존재하지 않는 레벨 입니다."),
    MISSION_NOT_FOUND("존재하지 않는 미션 입니다."),

    ALREADY_MATCHED_PAIR("같은 레벨에서 매칭된 적 있는 페어입니다."),
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
