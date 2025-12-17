package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.exception.ExceptionMessage;
import vendingmachine.util.Parser;

public final class InputView {

    private InputView() {
    }

    public static int readVendingMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = readLine();
        return Parser.parseToInt(input);
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
