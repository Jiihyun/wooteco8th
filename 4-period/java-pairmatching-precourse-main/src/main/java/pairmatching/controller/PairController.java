package pairmatching.controller;

import pairmatching.domain.GameCommand;
import pairmatching.domain.MatchingMachine;
import pairmatching.domain.MatchingResults;
import pairmatching.domain.Pairs;
import pairmatching.domain.RequiredMatchingInfo;
import pairmatching.dto.MatchingRequest;
import pairmatching.util.RetryHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    public void run() {
        GameCommand gameCommand = RetryHandler.retryOnInvalidInput(InputView::readCommand);
        MatchingRequest matchingRequest = RetryHandler.retryOnInvalidInput(InputView::readMatchingInfo);
        RequiredMatchingInfo requiredMatchingInfo = new RequiredMatchingInfo(matchingRequest.course(), matchingRequest.level(), matchingRequest.mission());
        MatchingMachine matchingMachine = new MatchingMachine(new MatchingResults());
        Pairs pairs = matchingMachine.match(requiredMatchingInfo);
        OutputView.showMatcingResult(pairs);
    }
}
