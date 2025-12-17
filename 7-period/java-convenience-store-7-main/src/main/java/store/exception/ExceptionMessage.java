package store.exception;

public enum ExceptionMessage {

    INPUT_BLANK("잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_FILE_CONTENT("잘못된 파일 형식입니다."),
    PROMOTION_NOT_FOUND("존재하지 않는 프로모션입니다."),
    INVALID_PRODUCT_QUANTITY_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    PRODUCT_NOT_FOUND("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INVALID_PURCHASED_QUANTITY("최소 1개 이상 구매 가능합니다."),
    ANSWER_COMMAND_NOT_FOUND("잘못된 입력입니다. 다시 입력해 주세요."),
    OUT_OF_STOCK("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    ;
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
