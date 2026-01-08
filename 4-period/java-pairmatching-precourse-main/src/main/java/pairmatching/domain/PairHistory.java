package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.info.Level;

public class PairHistory {

    private final Map<PairInfo, Pairs> histories;

    public PairHistory() {
        this.histories = new HashMap<>();
    }

    public void put(PairInfo pairInfo, Pairs pairs) {
        histories.put(pairInfo, pairs);
    }

    public List<Pair> findByLevel(Level level) {
        return histories.entrySet().stream()
                .filter(entry -> entry.getKey().getLevel() == level)
                .map(Map.Entry::getValue)
                .flatMap(pairs -> pairs.getPairs().stream())
                .toList();
    }
}
