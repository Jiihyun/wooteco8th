package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Pair {

    private final List<String> pair;

    public Pair(String crew1, String crew2) {
        this.pair = new ArrayList<>(Arrays.asList(crew1, crew2));
    }

    public void addLastCrew(String crew) {
        pair.add(crew);
    }

    public List<String> getPair() {
        return pair;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pair pair1)) {
            return false;
        }

        return new HashSet<>(getPair()).equals(new HashSet<>(pair1.getPair()));
    }

    @Override
    public int hashCode() {
        return new HashSet<>(getPair()).hashCode();
    }
}
