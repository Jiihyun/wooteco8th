package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.exception.ExceptionMessage;
import lotto.util.Parser;

public final class InputView {

    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static int readPurchasedAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Parser.parseToInt(readLine());
    }

    public static List<Integer> readWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        List<String> input = Parser.parseByDelimiter(readLine(), DELIMITER);
        return input.stream()
                .map(Parser::parseToInt)
                .toList();
    }

    public static int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
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
