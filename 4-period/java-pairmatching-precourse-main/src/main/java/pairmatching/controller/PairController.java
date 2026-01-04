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
            RequiredMatchingInfo requiredMatchingInfo = toRequiredMatchingInfo();
            RequiredMatchingInfo resolvedInfo = resolveIfDuplicate(matchingResults, requiredMatchingInfo);
            try {
                matchPair(matchingMachine, resolvedInfo);
            } catch (IllegalStateException illegalStateException) {
                OutputView.showError(illegalStateException.getMessage());
            }
        }
    }

    private RequiredMatchingInfo toRequiredMatchingInfo() {
        MatchingRequest matchingRequest = RetryHandler.retryOnInvalidInput(InputView::readMatchingInfo);
        return new RequiredMatchingInfo(
                matchingRequest.course(),
                matchingRequest.level(),
                matchingRequest.mission());
    }

    private RequiredMatchingInfo resolveIfDuplicate(MatchingResults matchingResults, RequiredMatchingInfo requiredMatchingInfo) {
        if (!hasMatchingResult(matchingResults, requiredMatchingInfo)) {
            return requiredMatchingInfo;
        }
        while (hasMatchingResult(matchingResults, requiredMatchingInfo)) {
            if (isYes()) {
                matchingResults.remove(matchingResults.findByRequiredInfo(requiredMatchingInfo));
                return requiredMatchingInfo;
            }
            requiredMatchingInfo = toRequiredMatchingInfo();
        }
        return requiredMatchingInfo;
    }
    
    private boolean hasMatchingResult(MatchingResults matchingResults, RequiredMatchingInfo requiredMatchingInfo) {
        return matchingResults.existsByRequiredInfo(requiredMatchingInfo);
    }

    private boolean isYes() {
        AnswerCommand answer = RetryHandler.retryOnInvalidInput(InputView::readRematchingAnswer);
        return answer.isYes();
    }

    private void matchPair(MatchingMachine matchingMachine, RequiredMatchingInfo requiredMatchingInfo) {
        Pairs pairs = matchingMachine.match(requiredMatchingInfo);
        OutputView.showPairs(pairs);
    }

    private void findByCommand(GameCommand gameCommand, MatchingResults matchingResults) {
        if (gameCommand.isFind()) {
            RequiredMatchingInfo requiredMatchingInfo = toRequiredMatchingInfo();
            MatchingResult matchingResult = matchingResults.findByRequiredInfo(requiredMatchingInfo);
            OutputView.showMatchingResult(matchingResult);
        }
    }

    private void resetByCommand(GameCommand gameCommand, MatchingResults matchingResults) {
        if (gameCommand.isReset()) {
            matchingResults.reset();
            OutputView.showResetMessage();
        }
    }
}
