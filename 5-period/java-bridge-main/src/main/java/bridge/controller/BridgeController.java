package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.MovingCommand;
import bridge.domain.RetryCommand;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {

    public void run() {
        List<String> bridge = makeBridge();

        BridgeGame bridgeGame = new BridgeGame(bridge);
        move(bridgeGame);
        if (!bridgeGame.isSuccess()) {
            RetryCommand retryCommand = InputView.readGameCommand();
            if (retryCommand.wantQuit()) {
                OutputView.printResult(bridgeGame);
                return;
            }
            bridgeGame.retry();
            move(bridgeGame);
        }
        OutputView.printResult(bridgeGame);
    }

    private List<String> makeBridge() {
        int bridgeSize = InputView.readBridgeSize();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        return bridgeMaker.makeBridge(bridgeSize);
    }

    private void move(BridgeGame bridgeGame) {
        while (bridgeGame.keepGame()) {
            MovingCommand movingCommand = InputView.readMoving();
            bridgeGame.move(movingCommand);
            OutputView.printMap(bridgeGame);
        }
    }
}
