package pairmatching.domain;

import java.util.Objects;
import pairmatching.domain.info.Course;
import pairmatching.domain.info.Level;
import pairmatching.domain.info.Mission;

public class PairInfo {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public PairInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
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
        if (!(o instanceof PairInfo pairInfo)) {
            return false;
        }

        return getCourse() == pairInfo.getCourse() && getLevel() == pairInfo.getLevel() && getMission() == pairInfo.getMission();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCourse());
        result = 31 * result + Objects.hashCode(getLevel());
        result = 31 * result + Objects.hashCode(getMission());
        return result;
    }
}
