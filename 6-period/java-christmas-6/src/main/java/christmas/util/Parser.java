package christmas.util;

import christmas.exception.ExceptionMessage;
import java.util.Arrays;
import java.util.List;

public final class Parser {

    private Parser() {
    }

    public static List<String> parseByDelimiter(String content, String delimiter) {
        return Arrays.stream(content.split(delimiter))
                .toList();
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }
}
