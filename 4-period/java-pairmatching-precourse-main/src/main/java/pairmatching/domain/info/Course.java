package pairmatching.domain.info;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum Course {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    ;

    private final String description;

    Course(String description) {
        this.description = description;
    }

    public static Course from(String input) {
        return Arrays.stream(Course.values())
                .filter(element -> element.description.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.COURSE_NOT_FOUND.getMessage()));
    }
}
