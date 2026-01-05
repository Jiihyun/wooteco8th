package attendance.domain;

public enum AttendanceState {

    결석,
    지각,
    출석;

//    public static AttendanceState from(String input) {
//        return Arrays.stream(GameCommand.values())
//                .filter(element -> element.command.equals(input))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COMMAND_NOT_FOUND.getMessage()));
//    }

}
