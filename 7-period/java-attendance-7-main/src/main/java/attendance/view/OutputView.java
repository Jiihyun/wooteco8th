package attendance.view;

import attendance.dto.EditResult;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    public static final String ATTENDANCE_FORMAT = "MM월 dd일 EEEE HH:mm";
    private static final String CHECK_ATTENDANCE_FORMAT = NEW_LINE + "%s (출석)";

    private OutputView() {
    }

    public static void showCheckedAttendance(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT)
                .withLocale(Locale.forLanguageTag("ko"));
        String format = dateTime.format(formatter);
        System.out.println(CHECK_ATTENDANCE_FORMAT.formatted(format));
    }

    public static void showEditedAttendance(EditResult editResult) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT)
                .withLocale(Locale.forLanguageTag("ko"));
        String format = editResult.before().format(formatter);
        System.out.println(format
                + " -> "
                + editResult.after().format(DateTimeFormatter.ofPattern("HH:mm"))
                + " 수정 완료!");
    }

    public static void showError(String message) {
        System.out.println(message);
    }

    //    //12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!
}
