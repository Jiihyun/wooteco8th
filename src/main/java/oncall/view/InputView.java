package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.dto.OncallRequest;
import oncall.exception.ExceptionMessage;
import oncall.util.Parser;

public final class InputView {

    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static OncallRequest readMonthAndDay() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        List<String> token = Parser.parseByDelimiter(readLine(), DELIMITER);
        return OncallRequest.of(Parser.parseToInt(token.getFirst()), token.getLast());
    }

    public static List<String> readWeekdaySchedule() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Parser.parseByDelimiter(readLine(), DELIMITER);
    }

    public static List<String> readWeekendSchedule() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Parser.parseByDelimiter(readLine(), DELIMITER);
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
