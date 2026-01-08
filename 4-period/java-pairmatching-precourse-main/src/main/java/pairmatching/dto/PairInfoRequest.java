package pairmatching.dto;

import pairmatching.domain.info.Course;
import pairmatching.domain.info.Level;
import pairmatching.domain.info.Mission;

public record PairInfoRequest(
        Course course,
        Level level,
        Mission mission
) {
    public static PairInfoRequest of(String course, String level, String mission) {
        return new PairInfoRequest(
                Course.from(course),
                Level.from(level),
                Mission.from(level, mission)
        );
    }
}
