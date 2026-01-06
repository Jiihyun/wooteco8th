package attendance.view;

import attendance.domain.Attendance;
import attendance.domain.AttendanceState;
import attendance.domain.Expulsion;
import attendance.dto.EditResult;
import attendance.dto.ShowResult;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    public static final String ATTENDANCE_FORMAT = "MM월 dd일 EEEE HH:mm";
    private static final String CHECK_ATTENDANCE_FORMAT = NEW_LINE + "%s (%s)";
    private static final String EDIT_ATTENDANCE_FORMAT = NEW_LINE + "%s (%s) -> %s (%s) 수정 완료!";
    private static final String SHOW_ATTENDANCE_FORMAT = NEW_LINE + """
            출석: %d회
            지각: %d회
            결석: %d회
            """;

    private OutputView() {
    }

    public static void showCheckedAttendance(LocalDateTime dateTime, AttendanceState attendanceState) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT)
                .withLocale(Locale.forLanguageTag("ko"));
        String format = dateTime.format(formatter);
        System.out.println(CHECK_ATTENDANCE_FORMAT.formatted(format, attendanceState.name()));
    }

    public static void showEditedAttendance(EditResult editResult) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT)
                .withLocale(Locale.forLanguageTag("ko"));
        String format = editResult.before().format(formatter);
        System.out.println(EDIT_ATTENDANCE_FORMAT.formatted(format, editResult.beforeAttendanceState(),
                editResult.after().format(DateTimeFormatter.ofPattern("HH:mm")), editResult.afterAttendanceState())
        );
    }

    public static void showResult(String nickname, ShowResult showResult) {
        System.out.println("이번 달 %s의 출석 기록입니다.".formatted(nickname));
        List<Attendance> attendances = showResult.attendances().stream()
                .sorted(Comparator.comparingInt(o -> o.getDateTime().getDayOfMonth()))
                .toList();

        for (Attendance attendance : attendances) {
            LocalDateTime dateTime = attendance.getDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT)
                    .withLocale(Locale.forLanguageTag("ko"));
            String format = dateTime.format(formatter);
            format = format.replaceAll("00:00", "--:--");
            System.out.println(CHECK_ATTENDANCE_FORMAT.formatted(format, attendance.getAttendanceState().name()));
        }
        System.out.println(SHOW_ATTENDANCE_FORMAT.formatted(showResult.attendance(), showResult.lateness(), showResult.noShow()));
        if (showResult.expulsion() != Expulsion.NONE) {
            System.out.println(NEW_LINE + showResult.expulsion().name() + " 대상자입니다.");
        }
    }
}
