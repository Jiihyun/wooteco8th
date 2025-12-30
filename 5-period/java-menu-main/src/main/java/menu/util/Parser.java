package menu.util;

import java.util.Arrays;
import java.util.List;

public final class Parser {

    private Parser() {
    }

    public static List<String> parseByDelimiter(String content, String delimiter) {
        return Arrays.stream(content.split(delimiter))
                .toList();
    }
}
