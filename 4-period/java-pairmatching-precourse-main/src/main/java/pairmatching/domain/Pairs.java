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

    public void addLastCrew(String lastCrew) {
        pairs.getLast().addLastCrew(lastCrew);
    }

    public void clear() {
        pairs.clear();
    }

    public boolean isEmpty() {
        return pairs.isEmpty();
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
