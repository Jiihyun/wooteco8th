package attendance.view;

import attendance.domain.Attendance;
import attendance.domain.Expulsion;
import attendance.dto.AttendanceResult;
import attendance.dto.EditedResult;
import attendance.dto.ShowResult;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    public static final String TIME_FORMAT = "HH:mm";
    public static final String ATTENDANCE_FORMAT = "MM월 dd일 EEEE " + TIME_FORMAT;
    private static final String CHECK_ATTENDANCE_FORMAT = NEW_LINE + "%s (%s)";
    private static final String EDIT_ATTENDANCE_FORMAT = NEW_LINE + "%s (%s) -> %s (%s) 수정 완료!";
    private static final String SHOW_ATTENDANCE_FORMAT = NEW_LINE + """
            출석: %d회
            지각: %d회
            결석: %d회
            """;

    private OutputView() {
    }

    public static void showCheckedAttendance(AttendanceResult attendanceResult) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT, Locale.KOREAN);
        String dateTime = attendanceResult.dateTime().format(formatter);
        System.out.println(NEW_LINE + CHECK_ATTENDANCE_FORMAT.formatted(dateTime, attendanceResult.attendanceState().name()));
    }

    public static void showEditedAttendance(EditedResult editedResult) {
        String beforeDateTime = editedResult.before()
                .format(DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT, Locale.KOREAN));
        String afterDateTime = editedResult.after()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));

        System.out.println(EDIT_ATTENDANCE_FORMAT.formatted(beforeDateTime, editedResult.beforeAttendanceState(),
                afterDateTime, editedResult.afterAttendanceState()));
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
//
//    public static void showExplusion(List<ShowResult> results) {
//        System.out.println("제적 위험자 조회 결과" + NEW_LINE);
//        String format = "- %s: 결석 %d회, 지각 %d회 (%s)";
//        results.sort(
//                Comparator.comparing(ShowResult::expulsion)
//                        .thenComparing(
//                                ShowResult::noShow,
//                                Comparator.reverseOrder()
//                        )
//                        .thenComparing(
//                                ShowResult::lateness,
//                                Comparator.reverseOrder()
//                        )
//                        .thenComparing(
//                                result -> result.attendances().getFirst().getNickname()
//                        )
//        );
//        for (ShowResult result : results) {
//            System.out.println(format.formatted(result.attendances().getFirst().getNickname(),
//                    result.noShow(), result.lateness(), result.expulsion().name()));
//        }
//    }
}
