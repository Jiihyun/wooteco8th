package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.domain.info.Course;
import pairmatching.exception.ExceptionMessage;
import pairmatching.util.FileReader;

public class MatchingProcessor {

    private final PairHistory pairHistory;

    public MatchingProcessor(PairHistory pairHistory) {
        this.pairHistory = pairHistory;
    }

    public boolean isMatched(PairInfo pairInfo) {
        return pairHistory.containsKey(pairInfo);
    }

    public Pairs process(PairInfo pairInfo) {
        List<String> crews = readCrews(pairInfo.getCourse());
        Pairs pairs = new Pairs();
        List<Pair> historyByLevel = pairHistory.findByLevel(pairInfo.getLevel());
        for (int tryCount = 0; tryCount < 3; tryCount++) {
            crews = Randoms.shuffle(crews);
            pairs.clear();
            for (int i = 0; i < crews.size() - 1; i += 2) {
                String crew1 = crews.get(i);
                String crew2 = crews.get(i + 1);
                Pair pair = new Pair(crew1, crew2);
                if (historyByLevel.contains(pair)) {
                    break;
                }
                pairs.add(pair);
            }
            if (pairs.isFull(crews.size() / 2)) {
                break;
            }
        }
        if (pairs.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_MATCH.getMessage());
        }
        if (crews.size() % 2 != 0) {
            pairs.addLastCrew(crews.getLast());
        }
        pairHistory.put(pairInfo, pairs);
        return pairs;
    }

    private List<String> readCrews(Course course) {
        if (course == Course.BACKEND) {
            return FileReader.readBackend();
        }
        return FileReader.readFrontend();
    }

    public Pairs searchPairsByPairInfo(PairInfo pairInfo) {
        return pairHistory.findByPairInfo(pairInfo);
    }

    public void clear() {
        pairHistory.clear();
    }
}
