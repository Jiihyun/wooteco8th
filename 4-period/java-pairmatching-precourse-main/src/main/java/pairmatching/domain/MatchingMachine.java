package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.util.FileReader;

public class MatchingMachine {
//- 같은 레벨에서 이미 페어로 만난적이 있는 크루끼리 다시 페어로 매칭 된다면 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다.
//- 3회 시도까지 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없으면 에러 메시지를 출력한다.

    public Pairs match(RequiredMatchingInfo requiredMatchingInfo) {
        List<String> crewNames = readCrewNames(requiredMatchingInfo.isBackend());
        List<String> shuffledNames = Randoms.shuffle(crewNames);
        Pairs pairs = new Pairs();
        if (shuffledNames.size() % 2 == 0) {
            for (int i = 0; i < shuffledNames.size() - 1; i++) {
                Crew crew1 = new Crew(shuffledNames.get(i));
                Crew crew2 = new Crew(shuffledNames.get(i + 1));
                pairs.add(new Pair(List.of(crew1, crew2)));
            }
        } else {
            for (int i = 0; i < shuffledNames.size() - 3; i++) {
                Crew crew1 = new Crew(shuffledNames.get(i));
                Crew crew2 = new Crew(shuffledNames.get(i + 1));
                pairs.add(new Pair(List.of(crew1, crew2)));
            }
            int size = shuffledNames.size();
            Crew crew1 = new Crew(shuffledNames.get(size - 3));
            Crew crew2 = new Crew(shuffledNames.get(size - 3));
            Crew crew3 = new Crew(shuffledNames.getLast());
            pairs.add(new Pair(List.of(crew1, crew2, crew3)));
        }
        return pairs;
    }

    private List<String> readCrewNames(boolean isBackend) {
        if (isBackend) {
            return FileReader.readBackendCrewNames();
        }
        return FileReader.readFrontendCrewNames();
    }
}
