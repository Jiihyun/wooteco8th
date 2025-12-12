package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ExceptionMessage;
import lotto.util.Parser;

public final class InputView {

    private InputView() {
    }

    public static int readPurchasedAmount() {
        System.out.println("구입금액을 입력해 주세요.");
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
