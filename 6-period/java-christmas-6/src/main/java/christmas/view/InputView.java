package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionMessage;
import christmas.util.Parser;

public final class InputView {

    private InputView() {
    }

    public int readDate() {
        System.out.println("""
                안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
                12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)""");
        return Parser.parseToInt(readLine());
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
