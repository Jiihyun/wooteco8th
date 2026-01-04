package pairmatching.domain;

import java.util.HashSet;
import java.util.Set;
import pairmatching.exception.ExceptionMessage;

public class MatchingResults {

    private final Set<MatchingResult> matchingResults;

    public MatchingResults() {
        this.matchingResults = new HashSet<>();
    }

    public void add(MatchingResult matchingResult) {
        matchingResults.add(matchingResult);
    }

    public boolean hasDuplicatedLevelPair(Level level, Pair pair) {
        return matchingResults.stream()
                .filter(matchingResult -> matchingResult.isSameLevel(level))
                .anyMatch(matchingResult -> matchingResult.contains(pair));
    }

    public boolean existsByRequiredInfo(RequiredMatchingInfo requiredMatchingInfo) {
        return matchingResults.stream()
                .anyMatch(matchingResult -> matchingResult.hasSameCondition(requiredMatchingInfo));
    }

    public MatchingResult findByRequiredInfo(RequiredMatchingInfo requiredMatchingInfo) {
        return matchingResults.stream()
                .filter(matchingResult -> matchingResult.hasSameCondition(requiredMatchingInfo))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MATHING_RESULT_NOT_FOUND.getMessage()));
    }

    public void remove(MatchingResult matchingResult) {
        matchingResults.remove(matchingResult);
    }

    public void reset() {
        matchingResults.clear();
    }
}
