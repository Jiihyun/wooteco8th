package pairmatching.domain;

public class MatchingResult {

    private final RequiredMatchingInfo requiredMatchingInfo;
    private final Pairs pairs;

    public MatchingResult(RequiredMatchingInfo requiredMatchingInfo, Pairs pairs) {
        this.requiredMatchingInfo = requiredMatchingInfo;
        this.pairs = pairs;
    }

    public boolean isSameLevel(Level other) {
        return requiredMatchingInfo.getLevel() == other;
    }

    public boolean hasSameCondition(RequiredMatchingInfo requiredMatchingInfo) {
        return this.requiredMatchingInfo.getCourse() == requiredMatchingInfo.getCourse()
                && this.requiredMatchingInfo.getLevel() == requiredMatchingInfo.getLevel()
                && this.requiredMatchingInfo.getMission() == requiredMatchingInfo.getMission();
    }

    public Pairs getPairs() {
        return pairs;
    }
}
