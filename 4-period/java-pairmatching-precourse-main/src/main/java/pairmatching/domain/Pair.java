package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Pair {

    private final List<Crew> pairCrew;

    public Pair(List<Crew> pairCrew) {
        this.pairCrew = pairCrew;
    }

    public void add(Crew crew) {
        pairCrew.add(crew);
    }

    public List<String> getPairCrew() {
        return pairCrew.stream()
                .map(Crew::getName)
                .toList();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pair pair)) {
            return false;
        }
        return Objects.equals(getPairCrew(), pair.getPairCrew());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPairCrew());
    }
}
