package bridge.domain;

import bridge.exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 20;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateSize(size);
        List<String> bridge = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int number = bridgeNumberGenerator.generate();
            bridge.add(MovingCommand.fromCondition(number));
        }
        return bridge;
    }

    private void validateSize(int size) {
        if (isOutOfRange(size)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BRIDGE_SIZE.getMessage());
        }
    }

    private boolean isOutOfRange(int size) {
        return size < MIN_SIZE || size > MAX_SIZE;
    }
}
