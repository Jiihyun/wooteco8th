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

    public static List<String> readCoachName() {
        System.out.println(NEW_LINE + "코치의 이름을 입력해 주세요. (, 로 구분)");
        String input = readLine();
        validateInput(input);
        return Parser.parseByDelimiter(input, DELIMITER);
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_BLANK.getMessage());
        }
    }

    public static List<String> readCantEatMenu(String name) {
        System.out.println(NEW_LINE + "%s(이)가 못 먹는 메뉴를 입력해 주세요.".formatted(name));
        String input = readLine();
        return Parser.parseByDelimiter(input, DELIMITER);
    }


    private static String readLine() {
        return Console.readLine().strip();
    }
}
