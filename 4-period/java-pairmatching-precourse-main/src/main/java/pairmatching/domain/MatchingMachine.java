package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.exception.ExceptionMessage;
import pairmatching.util.FileReader;

public class MatchingMachine {

    private static final int MAX_TRY = 3;

    private final MatchingResults matchingResults;

    public MatchingMachine(MatchingResults matchingResults) {
        this.matchingResults = matchingResults;
    }

    public Pairs match(RequiredMatchingInfo requiredMatchingInfo) {
        List<String> crewNames = readCrewNames(requiredMatchingInfo);
        Level level = requiredMatchingInfo.getLevel();
        Pairs pairs = matchPairs(crewNames, level, MAX_TRY);
        matchingResults.add(new MatchingResult(requiredMatchingInfo, pairs));
        return pairs;
    }

    private Pairs matchPairs(List<String> crewNames, Level level, int maxTry) {
        for (int attempt = 0; attempt < maxTry; attempt++) {
            Pairs candidate = createPairs(crewNames);
            if (!candidate.hasDuplicateWith(level, matchingResults)) {
                return candidate;
            }
        }
        throw new IllegalStateException(ExceptionMessage.ALREADY_MATCHED_PAIR.getMessage());
    }

    private Pairs createPairs(List<String> crewNames) {
        List<String> shuffledNames = Randoms.shuffle(crewNames);
        Pairs pairs = new Pairs();

        for (int i = 0; i < shuffledNames.size() - 1; i += 2) {
            Crew crew1 = new Crew(shuffledNames.get(i));
            Crew crew2 = new Crew(shuffledNames.get(i + 1));
            pairs.add(new Pair(new ArrayList<>(Arrays.asList(crew1, crew2))));
        }
        addIfOddCrews(pairs, shuffledNames);
        return pairs;
    }

    private void addIfOddCrews(Pairs pairs, List<String> shuffledNames) {
        if (shuffledNames.size() % 2 != 0) {
            pairs.addLastCrew(new Crew(shuffledNames.getLast()));
        }
    }

    private List<String> readCrewNames(RequiredMatchingInfo requiredMatchingInfo) {
        if (requiredMatchingInfo.isBackend()) {
            return FileReader.readBackendCrewNames();
        }
        return FileReader.readFrontendCrewNames();
    }
}
