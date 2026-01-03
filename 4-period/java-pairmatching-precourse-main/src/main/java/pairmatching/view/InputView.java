package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.GameCommand;
import pairmatching.exception.ExceptionMessage;

public final class InputView {

    private static final String CHOOSE_FUNCTION_MSG = """
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료""";

    private InputView() {
    }

    public static GameCommand readCommand() {
        System.out.println(CHOOSE_FUNCTION_MSG);
        return GameCommand.from(readLine());
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
