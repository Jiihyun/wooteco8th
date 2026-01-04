package pairmatching.domain;

import java.util.HashSet;
import java.util.List;
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

    //todo: 검증 필요
    public boolean hasDuplicatedLevelPair(Level level, Pair pair) {
        List<MatchingResult> sameLevelPairs = findByLevel(level);
        for (MatchingResult sameLevelPair : sameLevelPairs) {
            Pairs pairs = sameLevelPair.getPairs();
            return pairs.getPairs().stream()
                    .anyMatch(savedPair -> savedPair.equals(pair));
        }
        return false;
    }

    private List<MatchingResult> findByLevel(Level level) {
        return matchingResults.stream()
                .filter(matchingResult -> matchingResult.isSameLevel(level))
                .toList();
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
