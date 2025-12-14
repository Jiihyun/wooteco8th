package lotto.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 포맷입니다."),
    INVALID_PURCHASED_AMOUNT_UNIT("구입단위는 1_000원 이어야 합니다."),
    NUMBER_OUT_OF_RANGE("로또 번호의 범위가 올바르지 않습니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("로또 번호는 서로 중복될 수 없습니다."),
    ;
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
