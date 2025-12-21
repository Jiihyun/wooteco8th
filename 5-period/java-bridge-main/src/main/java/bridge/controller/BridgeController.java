package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.domain.BridgeLog;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.MovingCommand;
import bridge.domain.RetryCommand;
import bridge.dto.TotalResult;
import bridge.util.RetryHandler;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {

    public void run() {
        List<String> bridge = RetryHandler.retryOnInvalidInput(this::makeBridge);
        BridgeGame bridgeGame = new BridgeGame(bridge);
        BridgeLog bridgeLog = move(bridgeGame);
        TotalResult totalResult = new TotalResult(bridgeLog, bridgeGame.isSuccess(), bridgeGame.getTryCount());
        OutputView.printResult(totalResult);
    }

    private List<String> makeBridge() {
        int bridgeSize = InputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(bridgeSize);
    }

    private BridgeLog move(BridgeGame bridgeGame) {
        BridgeLog bridgeLog = new BridgeLog();
        while (bridgeGame.keepGame()) {
            MovingCommand movingCommand = RetryHandler.retryOnInvalidInput(InputView::readMoving);
            bridgeGame.move(movingCommand, bridgeLog);
            OutputView.printMap(bridgeLog);
            if (wantQuit(bridgeGame, bridgeLog)) {
                break;
            }
        }
        return bridgeLog;
    }

    private boolean wantQuit(BridgeGame bridgeGame, BridgeLog bridgeLog) {
        if (bridgeGame.isFailed()) {
            RetryCommand retryCommand = RetryHandler.retryOnInvalidInput(InputView::readGameCommand);
            if (retryCommand.wantQuit()) {
                return true;
            }
            bridgeGame.retry();
            bridgeLog.clear();
        }
        return false;
    }
}
