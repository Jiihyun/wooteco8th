package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderItemRequest;
import christmas.exception.ExceptionMessage;
import christmas.util.Parser;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputView {

    private static final Pattern ORDER_FORMAT = Pattern.compile("^([가-힣]+)-(\\d+)$");
    private static final String ORDER_ITEM_DELIMITER = ",";

    private InputView() {
    }

    public static int readDate() {
        System.out.println("""
                안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
                12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)""");
        return Parser.parseToInt(readLine());
    }

    public static List<OrderItemRequest> readOrderItems() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        List<String> parsedInput = Parser.parseByDelimiter(readLine(), ORDER_ITEM_DELIMITER);
        return parsedInput.stream()
                .map(InputView::createOrderItemRequest)
                .toList();
    }

    private static OrderItemRequest createOrderItemRequest(String item) {
        Matcher matcher = ORDER_FORMAT.matcher(item);
        validateOrderItemFormat(matcher);

        String name = matcher.group(1);
        int quantity = Parser.parseToInt(matcher.group(2));
        return new OrderItemRequest(name, quantity);
    }

    private static void validateOrderItemFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private static String readLine() {
        String input = Console.readLine().strip();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_BLANK.getMessage());
        }
    }
}
