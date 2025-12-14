package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.exception.ExceptionMessage;
import lotto.util.Parser;

public final class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String WINNING_NUMBER_DELIMITER = ",";

    private InputView() {
    }

    public static int readPurchasedAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Parser.parseToInt(readLine());
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println(NEW_LINE + "당첨 번호를 입력해 주세요.");
        List<String> parsedInput = Parser.parseByDelimiter(readLine(), WINNING_NUMBER_DELIMITER);
        return parsedInput.stream()
                .map(Parser::parseToInt)
                .toList();
    }

    public static int readBonusNumber() {
        System.out.println(NEW_LINE + "보너스 번호를 입력해 주세요.");
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
