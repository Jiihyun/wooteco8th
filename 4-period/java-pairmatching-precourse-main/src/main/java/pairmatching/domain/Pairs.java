package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pairs {

    private final List<Pair> pairs;

    public Pairs() {
        this.pairs = new ArrayList<>();
    }

    public void add(Pair pair) {
        pairs.add(pair);
    }

    public void addLastCrew(Crew crew) {
        pairs.getLast().add(crew);
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
