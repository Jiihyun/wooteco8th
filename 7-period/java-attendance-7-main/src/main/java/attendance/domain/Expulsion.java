package attendance.domain;

import java.util.Arrays;

public enum Expulsion {

    제적(6),
    면담(3),
    경고(2),
    NONE(0),
    ;

    private final int maxNoshowCount;

    Expulsion(int maxNoshowCount) {
        this.maxNoshowCount = maxNoshowCount;
    }

    public static Expulsion from(int maxNoshowCount) {
        return Arrays.stream(Expulsion.values())
                .filter(element -> element.maxNoshowCount <= maxNoshowCount)
                .findFirst()
                .orElse(NONE);
    }
}
