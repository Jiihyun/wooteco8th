package attendance.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class OutputView {

    private static final String CHECK_ATTENDANCE_FORMAT = "%s (출석)";

    private OutputView() {
    }

    public static void showCheckedAttendance(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm")
                .withLocale(Locale.forLanguageTag("ko"));
        String format = dateTime.format(formatter);
        System.out.println(CHECK_ATTENDANCE_FORMAT.formatted(format));
    }
}
