package lotto.exception;

public enum ExceptionMessage {

    CANNOT_READ_FILE("파일을 읽는 도중 문제가 발생했습니다."),
    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 형식입니다."),
    INVALID_NUMBER_RANGE("유효하지 않은 로또 숫자 범위입니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    _NOT_FOUND("존재하지 않는 oo입니다."),
    _ALREADY_EXISTS("oo가 이미 존재합니다."),
    DUPLICATED_NUMBER("중복된 로또 번호입니다."),

    INVALID_AMOUNT("구매 불가능한 금액입니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
