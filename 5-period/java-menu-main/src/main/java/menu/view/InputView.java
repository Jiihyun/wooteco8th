package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import menu.exception.ExceptionMessage;
import menu.util.Parser;

public final class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public List<String> readCoachName() {
        System.out.println(NEW_LINE + "코치의 이름을 입력해 주세요. (, 로 구분)");
        String input = readLine();
        return Parser.parseByDelimiter(input, DELIMITER);
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
