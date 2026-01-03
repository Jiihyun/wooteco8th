package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.exception.ExceptionMessage;
import pairmatching.util.FileReader;

public class MatchingMachine {
//- 같은 레벨에서 이미 페어로 만난적이 있는 크루끼리 다시 페어로 매칭 된다면 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다. if -break
//- 3회 시도까지 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없으면 에러 메시지를 출력한다.

    private final MatchingResults matchingResults;
    private int tryCount = 3;

    public MatchingMachine(MatchingResults matchingResults) {
        this.matchingResults = matchingResults;
    }

    public Pairs match(RequiredMatchingInfo requiredMatchingInfo) {
        tryCount = 3;
        List<String> crewNames = readCrewNames(requiredMatchingInfo.isBackend());
        Level level = requiredMatchingInfo.getLevel();
        Pairs pairs = new Pairs();
        while (true) {
            if (isSuccess(crewNames, requiredMatchingInfo, level, pairs)) {
                break;
            }
        }
        return pairs;
    }

    private boolean isSuccess(List<String> crewNames, RequiredMatchingInfo requiredMatchingInfo, Level level, Pairs pairs) {
        List<String> shuffledNames = Randoms.shuffle(crewNames);

        for (int i = 0; i < shuffledNames.size() - 1; i++) {
            Crew crew1 = new Crew(shuffledNames.get(i));
            Crew crew2 = new Crew(shuffledNames.get(i + 1));
            Pair pair = new Pair(List.of(crew1, crew2));
            if (matchingResults.hasDuplicatedLevelPair(level, pair)) {
                tryCount--;
                if (tryCount < 0) {
                    throw new IllegalStateException(ExceptionMessage.ALREADY_MATCHED_PAIR.getMessage());
                }
                return false;
            }
            pairs.add(pair);
        }
        if (shuffledNames.size() % 2 != 0) {
            pairs.addLastCrew(new Crew(shuffledNames.getLast()));
        }
        matchingResults.add(MatchingResult.of(requiredMatchingInfo, pairs));
        return true;
    }

    private List<String> readCrewNames(boolean isBackend) {
        if (isBackend) {
            return FileReader.readBackendCrewNames();
        }
        return FileReader.readFrontendCrewNames();
    }
}
