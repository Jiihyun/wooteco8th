package attendance.view;

import attendance.domain.Attendance;
import attendance.domain.Expulsion;
import attendance.dto.AttendanceResult;
import attendance.dto.CrewAttendanceResult;
import attendance.dto.EditedResult;
import java.time.LocalTime;
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
    private static final String ABSENCE_FORMAT = "--:--";
    private static final String SHOW_ATTENDANCE_FORMAT = NEW_LINE + """
            출석: %d회
            지각: %d회
            결석: %d회
            """;
    private static final String ATTENDANCE_RESULT_FORMAT = NEW_LINE + "%s 대상자입니다.";

    private OutputView() {
    }

    public static void showCheckedAttendance(AttendanceResult attendanceResult) {
        DateTimeFormatter formatter = getAttendanceFormatter();
        String dateTime = attendanceResult.dateTime().format(formatter);
        System.out.println(CHECK_ATTENDANCE_FORMAT.formatted(dateTime, attendanceResult.attendanceState().name()));
    }

    private static DateTimeFormatter getAttendanceFormatter() {
        return DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT, Locale.KOREAN);
    }

    public static void showEditedAttendance(EditedResult editedResult) {
        String beforeDateTime = editedResult.before()
                .format(DateTimeFormatter.ofPattern(ATTENDANCE_FORMAT, Locale.KOREAN));
        String afterDateTime = editedResult.after()
                .format(DateTimeFormatter.ofPattern(TIME_FORMAT));

        System.out.println(EDIT_ATTENDANCE_FORMAT.formatted(beforeDateTime, editedResult.beforeAttendanceState(),
                afterDateTime, editedResult.afterAttendanceState()));
    }

    public static void showAllAttendanceByCrew(String nickname, CrewAttendanceResult crewAttendanceResult) {
        System.out.println("이번 달 %s의 출석 기록입니다.".formatted(nickname));
        showAttendance(crewAttendanceResult);
        showAttendanceResult(crewAttendanceResult);
    }

    private static void showAttendance(CrewAttendanceResult crewAttendanceResult) {
        List<Attendance> attendances = sortAttendanceByDay(crewAttendanceResult);

        for (Attendance attendance : attendances) {
            DateTimeFormatter formatter = getAttendanceFormatter();
            String dateTime = attendance.getDateTime().format(formatter);
            dateTime = dateTime.replace(LocalTime.MIN.toString(), ABSENCE_FORMAT);

            System.out.println(CHECK_ATTENDANCE_FORMAT.formatted(dateTime, attendance.getAttendanceState().name()));
        }
    }

    private static List<Attendance> sortAttendanceByDay(CrewAttendanceResult crewAttendanceResult) {
        return crewAttendanceResult.attendances().stream()
                .sorted(Comparator.comparingInt(o -> o.getDateTime().getDayOfMonth()))
                .toList();
    }

    private static void showAttendanceResult(CrewAttendanceResult crewAttendanceResult) {
        System.out.println(SHOW_ATTENDANCE_FORMAT.formatted(
                crewAttendanceResult.attendanceCount(),
                crewAttendanceResult.lateCount(),
                crewAttendanceResult.absentCount()));
        if (crewAttendanceResult.expulsion() != Expulsion.NONE) {
            System.out.println(ATTENDANCE_RESULT_FORMAT.formatted(crewAttendanceResult.expulsion().name()));
        }
    }
//
//    public static void showExplusion(List<ShowResult> results) {
//        System.out.println("제적 위험자 조회 결과" + NEW_LINE);
//        String format = "- %s: 결석 %d회, 지각 %d회 (%s)";
//        results.sort(
//                Comparator.comparing(ShowResult::expulsion)
//                        .thenComparing(
//                                ShowResult::absentCount,
//                                Comparator.reverseOrder()
//                        )
//                        .thenComparing(
//                                ShowResult::lateCount,
//                                Comparator.reverseOrder()
//                        )
//                        .thenComparing(
//                                result -> result.attendances().getFirst().getNickname()
//                        )
//        );
//        for (ShowResult result : results) {
//            System.out.println(format.formatted(result.attendances().getFirst().getNickname(),
//                    result.absentCount(), result.lateCount(), result.expulsion().name()));
//        }
//    }
}
