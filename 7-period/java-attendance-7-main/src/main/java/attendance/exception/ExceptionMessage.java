package attendance.exception;

public enum ExceptionMessage {

    CANNOT_READ_FILE("파일을 읽는 도중 문제가 발생했습니다."),
    INPUT_BLANK("잘못된 형식을 입력하였습니다."),
    INVALID_NUMBER("잘못된 형식을 입력하였습니다."),
    COMMAND_NOT_FOUND("잘못된 형식을 입력하였습니다."),
    NICKNAME_NOT_FOUND("등록되지 않은 닉네임입니다."),
    HISTORY_ALREADY_EXISTS("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),
    HISTORY_NOT_EXISTS("출석이 존재하지 않습니다."),
    CANNOT_ATTENDANCE("12월 %d일 %s은 등교일이 아닙니다."),
    CAMPUS_CLOSED_TIME("캠퍼스 운영 시간에만 출석이 가능합니다."),
    CANNOT_EDIT("아직 수정할 수 없습니다."),
    DUPLICATED("중복된 oo입니다."),
    INVALID_TIME("잘못된 형식을 입력하였습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormattedMessage(Object... args) {
        return message.formatted(args);
    }
}
