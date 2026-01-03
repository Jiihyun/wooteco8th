package pairmatching.domain;

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
}
