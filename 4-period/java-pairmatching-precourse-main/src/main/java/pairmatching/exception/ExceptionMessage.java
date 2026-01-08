package pairmatching.exception;

public enum ExceptionMessage {

    CANNOT_READ_FILE("파일을 읽는 도중 문제가 발생했습니다."),
    INPUT_BLANK("빈 값을 입력하셨습니다."),
    COMMAND_NOT_FOUND("존재하지 않는 명령어입니다."),
    COURSE_NOT_FOUND("존재하지 않는 과정입니다."),
    LEVEL_NOT_FOUND("존재하지 않는 레벨입니다."),
    MISSION_NOT_FOUND("존재하지 않는 미션입니다."),
    CANNOT_MATCH("매칭을 진행할 수 없습니다."),
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
