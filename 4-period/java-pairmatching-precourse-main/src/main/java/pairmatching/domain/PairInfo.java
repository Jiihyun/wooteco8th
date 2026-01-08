package pairmatching.domain;

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
}
