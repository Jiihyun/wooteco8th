package attendance.view;

import attendance.domain.command.Command;
import attendance.exception.ExceptionMessage;
import attendance.util.Parser;
import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private static final String TIME_FORMAT = "HH:mm";

    private InputView() {
    }

    public static Command readCommand(LocalDate dateOfToday) {
        System.out.println(INTRO_MESSAGE.formatted(
                dateOfToday.getMonthValue(),
                dateOfToday.getDayOfMonth(),
                dateOfToday.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
        return Command.from(readLine());
    }

    public static String readNickname() {
        System.out.println("닉네임을 입력해 주세요.");
        return readLine();
    }

    public static LocalTime readArrivedTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return Parser.parseTime(readLine(), timeFormatter);
    }

    public static String readEditedNickname() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return readLine();
    }

    public static int readDayForEdit() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Parser.parseToInt(readLine());
    }

    public static LocalTime readEditedTime() {
        System.out.println("언제로 변경하겠습니까?");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return Parser.parseTime(readLine(), timeFormatter);
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
