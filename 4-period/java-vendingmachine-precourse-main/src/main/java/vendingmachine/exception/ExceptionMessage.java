package vendingmachine.exception;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),
    INVALID_NUMBER("유효하지 않은 숫자입니다."),
    INVALID_FORMAT("유효하지 않은 포맷입니다."),
    INVALID_VENDING_MACHINE_MONEY("유효하지 않은 동전 범위입니다."),
    INVALID_COIN("유효하지 않은 동전 금액 입니다."),
    INVALID_PRODUCT_PRICE("상품 금액은 10단위여야 입니다."),
    INVALID_PRODUCT_PRICE_RANGE("상품 금액은 최소 100원이어야 입니다."),
    PRODUCT_NOT_EXISTS("존재하지 않는 상품입니다."),
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
