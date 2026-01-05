package attendance.view;

import attendance.domain.command.Command;
import attendance.exception.ExceptionMessage;
import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


public final class InputView {

    private static final String INTRO_MESSAGE = """
            
            오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요.
            1. 출석 확인
            2. 출석 수정
            3. 크루별 출석 기록 확인
            4. 제적 위험자 확인
            Q. 종료
            """;

    private InputView() {
    }

    public static Command readCommand(LocalDate dateOfToday) {
        System.out.println(INTRO_MESSAGE.formatted(
                dateOfToday.getMonthValue(),
                dateOfToday.getDayOfMonth(),
                dateOfToday.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
        return Command.from(readLine());
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
