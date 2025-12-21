package bridge.dto;

import bridge.domain.BridgeLog;

public record TotalResult(
        BridgeLog bridgeLog,
        boolean isSuccess,
        int tryCount
) {
}
