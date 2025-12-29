package christmas.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 형식입니다."),
    INVALID_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DUPLICATE_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_NOT_FOUND("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_QUANTITY_RANGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
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
