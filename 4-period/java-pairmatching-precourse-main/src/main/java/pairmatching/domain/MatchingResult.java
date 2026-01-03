package pairmatching.domain;

public class MatchingResult {

    private final Course course;
    private final Level level;
    private final Mission mission;
    private final Pairs pairs;

    public static MatchingResult of(RequiredMatchingInfo requiredMatchingInfo, Pairs pairs) {
        return new MatchingResult(
                requiredMatchingInfo.getCourse(),
                requiredMatchingInfo.getLevel(),
                requiredMatchingInfo.getMission(),
                pairs
        );
    }

    public MatchingResult(Course course, Level level, Mission mission, Pairs pairs) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairs = pairs;
    }

    public boolean isSameLevel(Level other) {
        return level == other;
    }

    public Pairs getPairs() {
        return pairs;
    }

    public boolean hasDuplicatedLevelPair() {
        for (Pair pair : pairs.getPairs()) {

        }

    }
}
