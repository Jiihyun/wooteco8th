package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import pairmatching.exception.ExceptionMessage;

public class Pair {

    private final List<Crew> pairCrew;

    public Pair(List<Crew> pairCrew) {
        validateUnique(pairCrew);
        this.pairCrew = new ArrayList<>(pairCrew);
    }

    private void validateUnique(List<Crew> pairCrew) {
        if (isDuplicated(pairCrew)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_CREW.getMessage());
        }
    }

    private boolean isDuplicated(List<Crew> pairCrew) {
        return pairCrew.stream()
                .distinct()
                .count() != pairCrew.size();
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

        return new HashSet<>(pairCrew).equals(new HashSet<>(pair.pairCrew));
    }

    @Override
    public int hashCode() {
        return new HashSet<>(pairCrew).hashCode();
    }
}
