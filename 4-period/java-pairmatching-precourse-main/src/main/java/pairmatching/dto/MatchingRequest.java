package pairmatching.dto;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public record MatchingRequest(
        Course course,
        Level level,
        Mission mission
) {
    public static MatchingRequest of(List<String> parsedInfo) {
        return new MatchingRequest(
                Course.from(parsedInfo.getFirst()),
                Level.from(parsedInfo.get(1)),
                Mission.from(Level.from(parsedInfo.get(1)), parsedInfo.getLast())
        );
    }
}
