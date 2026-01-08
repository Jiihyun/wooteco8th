package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.domain.info.Course;
import pairmatching.domain.info.Level;
import pairmatching.exception.ExceptionMessage;
import pairmatching.util.FileReader;

public class MatchingProcessor {

    public static final int TRY_COUNT = 3;
    private final PairHistory pairHistory;

    public MatchingProcessor(PairHistory pairHistory) {
        this.pairHistory = pairHistory;
    }

    public boolean isMatched(PairInfo pairInfo) {
        return pairHistory.containsKey(pairInfo);
    }

    public Pairs process(PairInfo pairInfo) {
        List<String> crews = readCrews(pairInfo.getCourse());
        Pairs pairs = match(crews, pairInfo.getLevel());
        pairHistory.put(pairInfo, pairs);
        return pairs;
    }

    private Pairs match(List<String> crews, Level level) {
        for (int tryCount = 0; tryCount < TRY_COUNT; tryCount++) {
            Pairs pairs = putPair(crews);
            if (!pairHistory.containsPairByLevel(pairs, level)) {
                return pairs;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.CANNOT_MATCH.getMessage());
    }

    private Pairs putPair(List<String> crews) {
        List<String> shuffledCrews = Randoms.shuffle(crews);
        Pairs pairs = new Pairs();
        for (int i = 0; i < shuffledCrews.size() - 1; i += 2) {
            String crew1 = shuffledCrews.get(i);
            String crew2 = shuffledCrews.get(i + 1);
            pairs.add(new Pair(crew1, crew2));
        }
        if (shuffledCrews.size() % 2 != 0) {
            pairs.addLastCrew(shuffledCrews.getLast());
        }
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
