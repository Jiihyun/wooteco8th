package oncall.util;

import java.util.Arrays;
import java.util.List;
import oncall.exception.ExceptionMessage;

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
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER.getMessage());
        }
    }
}
