package menu.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NAME("유효하지 않은 이름입니다."),
    INVALID_FORMAT("유효하지 않은 형식입니다."),
    INVALID_COACH_SIZE("유효하지 않은 코치 인원 범위입니다."),
    INVALID_MENU_SIZE("유효하지 않은 메뉴 범위입니다."),
    INVALID_MENU_CATEGORY("유효하지 않은 메뉴 카테고리 범위입니다."),
    MENU_NOT_EXISTS("존재하지 않는 메뉴입니다."),
    DUPLICATED_NAME("중복된 코치 이름입니다."),
    DUPLICATED_MENU("중복된 메뉴입니다."),
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
