package pairmatching.controller;

import pairmatching.domain.GameCommand;
import pairmatching.domain.MatchingMachine;
import pairmatching.domain.Pairs;
import pairmatching.domain.RequiredMatchingInfo;
import pairmatching.dto.MatchingRequest;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    public void run() {
        GameCommand gameCommand = InputView.readCommand();
        MatchingRequest matchingRequest = InputView.readMatchingInfo();
        RequiredMatchingInfo requiredMatchingInfo = new RequiredMatchingInfo(matchingRequest.course(), matchingRequest.level(), matchingRequest.mission());
        MatchingMachine matchingMachine = new MatchingMachine();
        Pairs pairs = matchingMachine.match(requiredMatchingInfo);
        OutputView.showMatcingResult(pairs);
    }
}
