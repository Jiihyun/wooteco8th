package attendance.domain;

import java.util.Arrays;

public enum Expulsion {

    제적(6),
    면담(3),
    경고(2),
    NONE(0),
    ;

    private final int absentThreshold;

    Expulsion(int absentThreshold) {
        this.absentThreshold = absentThreshold;
    }

    public static Expulsion from(int absentThreshold) {
        return Arrays.stream(Expulsion.values())
                .sorted(((o1, o2) -> o2.absentThreshold - o1.absentThreshold))
                .filter(element -> element.absentThreshold <= absentThreshold)
                .findFirst()
                .orElse(NONE);
    }
}
