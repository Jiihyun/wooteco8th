package pairmatching.domain;

import java.util.List;

public class Pair {

    private final List<Crew> pairCrew;

    public Pair(List<Crew> pairCrew) {
        this.pairCrew = pairCrew;
    }

    public List<String> getPairCrew() {
        return pairCrew.stream()
                .map(Crew::getName)
                .toList();
    }
}
