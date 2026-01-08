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

    public Pairs process(PairInfo pairInfo) {
        List<String> crews = readCrews(pairInfo.getCourse());
        Pairs pairs = new Pairs();
        List<Pair> historyByLevel = pairHistory.findByLevel(pairInfo.getLevel());
        for (int tryCount = 0; tryCount < 3; tryCount++) {
            List<String> shuffledCrews = Randoms.shuffle(crews);
            pairs.clear();
            for (int i = 0; i < shuffledCrews.size() - 1; i += 2) {
                String crew1 = shuffledCrews.get(i);
                String crew2 = shuffledCrews.get(i + 1);
                Pair pair = new Pair(crew1, crew2);
                if (!historyByLevel.contains(pair)) {
                    pairs.add(pair);
                }
            }
            if (shuffledCrews.size() % 2 != 0) {
                pairs.addLastCrew(shuffledCrews.getLast());
            }
        }
        if (pairs.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_MATCH.getMessage());
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
}
