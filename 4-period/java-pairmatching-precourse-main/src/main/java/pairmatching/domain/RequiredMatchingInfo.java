package pairmatching.domain;

import java.util.Objects;

public class RequiredMatchingInfo {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public RequiredMatchingInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public boolean isBackend() {
        return course.isBackend();
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof RequiredMatchingInfo that)) {
            return false;
        }

        return getCourse() == that.getCourse() && getLevel() == that.getLevel() && getMission() == that.getMission();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCourse());
        result = 31 * result + Objects.hashCode(getLevel());
        result = 31 * result + Objects.hashCode(getMission());
        return result;
    }
}
