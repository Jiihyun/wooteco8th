package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.dto.OncallRequest;
import oncall.exception.ExceptionMessage;
import oncall.util.Parser;


public final class InputView {

    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static OncallRequest readMonthAndStartDay() {
        List<String> parsedInput = Parser.parseByDelimiter(readLine(), DELIMITER);
        int month = Parser.parseToInt(parsedInput.getFirst());
        DayOfWeek dayOfWeek = DayOfWeek.from(parsedInput.getLast());
        return new OncallRequest(month, dayOfWeek);
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
