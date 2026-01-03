package pairmatching.controller;

import pairmatching.domain.AnswerCommand;
import pairmatching.domain.GameCommand;
import pairmatching.domain.MatchingMachine;
import pairmatching.domain.MatchingResult;
import pairmatching.domain.MatchingResults;
import pairmatching.domain.Pairs;
import pairmatching.domain.RequiredMatchingInfo;
import pairmatching.dto.MatchingRequest;
import pairmatching.util.RetryHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    public void run() {
        MatchingResults matchingResults = new MatchingResults();
        MatchingMachine matchingMachine = new MatchingMachine(matchingResults);
        runByCommand(matchingResults, matchingMachine);
    }

    private void runByCommand(MatchingResults matchingResults, MatchingMachine matchingMachine) {
        while (true) {
            GameCommand gameCommand = RetryHandler.retryOnInvalidInput(InputView::readCommand);
            if (gameCommand.isQuit()) {
                return;
            }
            matchByCommand(gameCommand, matchingResults, matchingMachine);
            RetryHandler.retryOnInvalidInput(() -> findByCommand(gameCommand, matchingResults));
            resetByCommand(gameCommand, matchingResults);
        }
    }

    private void matchByCommand(GameCommand gameCommand, MatchingResults matchingResults, MatchingMachine matchingMachine) {
        if (gameCommand.isMatching()) {
            MatchingRequest matchingRequest = RetryHandler.retryOnInvalidInput(InputView::readMatchingInfo);
            RequiredMatchingInfo requiredMatchingInfo = new RequiredMatchingInfo(matchingRequest.course(), matchingRequest.level(), matchingRequest.mission());
            if (matchingResults.existsByRequiredInfo(requiredMatchingInfo)) {
                requiredMatchingInfo = readRematchInfo(matchingResults, requiredMatchingInfo);
            }
            Pairs pairs = matchingMachine.match(requiredMatchingInfo);
            OutputView.showPairs(pairs);
        }
    }

    private RequiredMatchingInfo readRematchInfo(MatchingResults matchingResults, RequiredMatchingInfo requiredMatchingInfo) {
        MatchingRequest matchingRequest;
        AnswerCommand answerCommand = RetryHandler.retryOnInvalidInput(InputView::readRematchingAnswer);
        while (answerCommand.isNo()) {
            matchingRequest = RetryHandler.retryOnInvalidInput(InputView::readMatchingInfo);
            requiredMatchingInfo = new RequiredMatchingInfo(matchingRequest.course(), matchingRequest.level(), matchingRequest.mission());
            if (matchingResults.existsByRequiredInfo(requiredMatchingInfo)) {
                answerCommand = RetryHandler.retryOnInvalidInput(InputView::readRematchingAnswer);
            }
        }
        return requiredMatchingInfo;
    }

    private void findByCommand(GameCommand gameCommand, MatchingResults matchingResults) {
        if (gameCommand.isFind()) {
            MatchingRequest matchingRequest = RetryHandler.retryOnInvalidInput(InputView::readMatchingInfo);
            RequiredMatchingInfo requiredMatchingInfo = new RequiredMatchingInfo(
                    matchingRequest.course(),
                    matchingRequest.level(),
                    matchingRequest.mission());
            MatchingResult matchingResult = matchingResults.findByRequiredInfo(requiredMatchingInfo);
            OutputView.showMatchingResult(matchingResult);
        }
    }

    private void resetByCommand(GameCommand gameCommand, MatchingResults matchingResults) {
        if (gameCommand.isReset()) {
            matchingResults.reset();
        }
    }
}
