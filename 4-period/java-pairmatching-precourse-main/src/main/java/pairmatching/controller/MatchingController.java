package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.domain.MatchingProcessor;
import pairmatching.domain.PairHistory;
import pairmatching.domain.PairInfo;
import pairmatching.domain.Pairs;
import pairmatching.domain.RematchCommand;
import pairmatching.dto.PairInfoRequest;
import pairmatching.util.RetryHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {

    public void run() {
        MatchingProcessor matchingProcessor = new MatchingProcessor(new PairHistory());
        while (true) {
            Command command = RetryHandler.retryOnInvalidInput(InputView::readCommand);
            processCommand(command, matchingProcessor);
            if (command == Command.QUIT) {
                return;
            }
        }
    }

    private void processCommand(Command command, MatchingProcessor matchingProcessor) {
        if (command == Command.MATCH) {
            match(matchingProcessor);
        }
        if (command == Command.SEARCH) {
            search(matchingProcessor);
        }
        if (command == Command.CLEAR) {
            clear(matchingProcessor);
        }
    }

    private void match(MatchingProcessor matchingProcessor) {
        while (true) {
            PairInfo pairInfo = RetryHandler.retryOnInvalidInput(this::readPairInfo);
            if (!matchingProcessor.isMatched(pairInfo)) {
                Pairs pairs = matchingProcessor.process(pairInfo);
                OutputView.showPairs(pairs);
                break;
            }
            RematchCommand rematchCommand = RetryHandler.retryOnInvalidInput(InputView::readRematch);
            if (rematchCommand == RematchCommand.네) {
                Pairs pairs = matchingProcessor.process(pairInfo);
                OutputView.showPairs(pairs);
                break;
            }
        }
    }

    private PairInfo readPairInfo() {
        PairInfoRequest pairInfoRequest = InputView.readPairInfo();
        return new PairInfo(pairInfoRequest.course(), pairInfoRequest.level(), pairInfoRequest.mission());
    }

    private void search(MatchingProcessor matchingProcessor) {
        PairInfo pairInfo = RetryHandler.retryOnInvalidInput(this::readPairInfo);
        Pairs pairs = matchingProcessor.searchPairsByPairInfo(pairInfo);
        OutputView.showPairs(pairs);
    }

    private void clear(MatchingProcessor matchingProcessor) {
        OutputView.showClear();
        matchingProcessor.clear();
    }
}
