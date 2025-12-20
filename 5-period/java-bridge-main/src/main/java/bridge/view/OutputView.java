package bridge.view;

import bridge.domain.BridgeGame;
import bridge.domain.MovingCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String BRIDGE_DELIMITER = " | ";
    private static final String MAP_FORMAT = "[ %s ]";

    private OutputView() {
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printMap(BridgeGame bridgeGame) {
        List<String> up = new ArrayList<>();
        List<String> down = new ArrayList<>();
        judgeAnswer(bridgeGame, up, down);

        System.out.println(MAP_FORMAT.formatted(String.join(BRIDGE_DELIMITER, up)));
        System.out.println(MAP_FORMAT.formatted(String.join(BRIDGE_DELIMITER, down)));
    }

    private static void judgeAnswer(BridgeGame bridgeGame, List<String> up, List<String> down) {
        List<String> userBridge = bridgeGame.getUserBridge();

        for (int i = 0; i < userBridge.size(); i++) {
            if (bridgeGame.isSame(i)) {
                printO(userBridge.get(i), up, down);
            }
            if (!bridgeGame.isSame(i)) {
                printX(userBridge.get(i), up, down);
            }
        }
    }

    private static void printO(String answer, List<String> up, List<String> down) {
        if (answer.equals(MovingCommand.U.name())) {
            up.add("O");
            down.add(" ");
        }
        if (answer.equals(MovingCommand.D.name())) {
            up.add(" ");
            down.add("O");
        }
    }

    private static void printX(String answer, List<String> up, List<String> down) {
        if (answer.equals(MovingCommand.U.name())) {
            up.add("X");
            down.add(" ");
        }
        if (answer.equals(MovingCommand.D.name())) {
            up.add(" ");
            down.add("X");
        }
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(BridgeGame bridgeGame) {
        System.out.println(NEW_LINE + "최종 게임 결과");
        printMap(bridgeGame);
        printIsSuccess(bridgeGame);
        System.out.println("총 시도한 횟수: %d".formatted(bridgeGame.getTryCount()));
    }

    private static void printIsSuccess(BridgeGame bridgeGame) {
        String format = NEW_LINE + "게임 성공 여부: %s";
        if (bridgeGame.isSuccess()) {
            System.out.println(format.formatted("성공"));
            return;
        }
        System.out.println(format.formatted("실패"));
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
