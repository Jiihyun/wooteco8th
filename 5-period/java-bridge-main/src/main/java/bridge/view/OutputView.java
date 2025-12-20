package bridge.view;

import bridge.domain.BridgeGame;
import bridge.domain.MovingCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public final class OutputView {

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

        List<String> bridge = bridgeGame.getBridge();
        List<String> userBridge = bridgeGame.getUserBridge();
        judgeAnswer(userBridge, bridge, up, down);

        System.out.println("[ " + String.join(" | ", up) + " ]");
        System.out.println("[ " + String.join(" | ", down) + " ]");
    }

    private static void judgeAnswer(List<String> userBridge, List<String> bridge, List<String> up, List<String> down) {
        for (int i = 0; i < userBridge.size(); i++) {
            String answer = userBridge.get(i);
            if (answer.equals(bridge.get(i))) {
                printO(answer, up, down);
            }
            if (!answer.equals(bridge.get(i))) {
                printX(answer, up, down);
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
        System.out.println("\n최종 게임 결과");
        printMap(bridgeGame);
        printIsSuccess(bridgeGame);
        System.out.println("총 시도한 횟수: %d".formatted(bridgeGame.getTryCount()));
    }

    private static void printIsSuccess(BridgeGame bridgeGame) {
        List<String> bridge = bridgeGame.getBridge();
        List<String> userBridge = bridgeGame.getUserBridge();
        for (int i = 0; i < userBridge.size(); i++) {
            String answer = userBridge.get(i);
            if (!answer.equals(bridge.get(i))) {
                System.out.println("\n게임 성공 여부: 실패");
            }
        }
        System.out.println("\n게임 성공 여부: 성공");
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
