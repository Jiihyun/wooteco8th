package attendance.domain.command;

import attendance.exception.ExceptionMessage;
import java.util.Arrays;

public enum Command {

    CHECK_ATTENDANCE("출석 확인", "1"),
    EDIT_ATTENDANCE("출석 수정", "2"),
    CHECK_ATTENDANCE_PER_CREW("크루별 출석 기록 확인", "3"),
    CHECK_DEPORTATION("제적 위험자 확인", "4"),
    QUIT("종료", "Q"),
    ;
    private final String description;
    private final String command;

    Command(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public static Command from(String input) {
        return Arrays.stream(Command.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
    }

    public boolean isCheckAttendance() {
        return this == CHECK_ATTENDANCE;
    }

    public boolean isEditAttendance() {
        return this == EDIT_ATTENDANCE;
    }

    public boolean isQuit() {
        return this == QUIT;
    }

    public boolean isCheckAttendancePerCrew() {
        return this == CHECK_ATTENDANCE_PER_CREW;
    }
}
