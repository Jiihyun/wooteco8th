package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.exception.ExceptionMessage;
import pairmatching.util.FileReader;

public class MatchingMachine {

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

        for (int i = 0; i < shuffledNames.size() - 1; i += 2) {
            Crew crew1 = new Crew(shuffledNames.get(i));
            Crew crew2 = new Crew(shuffledNames.get(i + 1));
            Pair pair = new Pair(new ArrayList<>(Arrays.asList(crew1, crew2)));
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
        matchingResults.add(new MatchingResult(requiredMatchingInfo, pairs));
        return true;
    }

    private List<String> readCrewNames(boolean isBackend) {
        if (isBackend) {
            return FileReader.readBackendCrewNames();
        }
        return FileReader.readFrontendCrewNames();
    }
}
