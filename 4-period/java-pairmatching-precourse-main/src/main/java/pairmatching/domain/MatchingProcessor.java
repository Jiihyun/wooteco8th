package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.domain.info.Course;
import pairmatching.util.FileReader;

public class MatchingProcessor {

    private final PairHistory pairHistory;

    public MatchingProcessor(PairHistory pairHistory) {
        this.pairHistory = pairHistory;
    }

    //- 같은 레벨에서 이미 페어로 만난적이 있는 크루끼리 다시 페어로 매칭 된다면 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다.
//- 3회 시도까지 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없으면 에러 메시지를 출력한다.
    public Pairs process(PairInfo pairInfo) {
        List<String> crews = readCrews(pairInfo.getCourse());
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
