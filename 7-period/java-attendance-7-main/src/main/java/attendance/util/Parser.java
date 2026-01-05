package attendance.util;

import attendance.exception.ExceptionMessage;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public final class Parser {

    private Parser() {
    }

    public static LocalTime parseTime(String time, DateTimeFormatter timeFormatter) {
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeException dateTimeException) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_TIME.getMessage());
        }
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
