package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.exception.ExceptionMessage;

public final class Parser {

    private Parser() {
    }

    public static int parseToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER.getMessage());
        }
    }

    public static List<String> parseByDelimiter(String expression, String delimiter) {
        return Arrays.stream(expression.split(delimiter))
                .toList();
    }
}
