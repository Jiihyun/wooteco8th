package bridge.view;

import bridge.domain.BridgeLog;
import bridge.domain.GameResult;
import bridge.domain.LogType;
import bridge.dto.TotalResult;
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
    public static void printMap(BridgeLog bridgeLog) {
        List<String> up = makeShape(bridgeLog.getUp());
        List<String> down = makeShape(bridgeLog.getDown());
        System.out.println(MAP_FORMAT.formatted(String.join(BRIDGE_DELIMITER, up)));
        System.out.println(MAP_FORMAT.formatted(String.join(BRIDGE_DELIMITER, down)));
    }

    private static List<String> makeShape(List<LogType> log) {
        return log.stream()
                .map(LogType::getShape)
                .toList();
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public static void printResult(TotalResult totalResult) {
        System.out.println(NEW_LINE + "최종 게임 결과");
        printMap(totalResult.bridgeLog());
        printIsSuccess(totalResult.isSuccess());
        System.out.println("총 시도한 횟수: %d".formatted(totalResult.tryCount()));
    }

    private static void printIsSuccess(boolean isSuccess) {
        String format = NEW_LINE + "게임 성공 여부: %s";
        GameResult gameResult = GameResult.from(isSuccess);
        System.out.println(format.formatted(gameResult.getDescription()));
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
