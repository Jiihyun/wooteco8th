package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;

public class PairHistory {

    private final Map<PairInfo, Pairs> histories;

    public PairHistory() {
        this.histories = new HashMap<>();
    }

    public void put(PairInfo pairInfo, Pairs pairs) {
        histories.put(pairInfo, pairs);
    }
}
