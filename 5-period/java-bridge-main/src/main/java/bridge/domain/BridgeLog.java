package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class BridgeLog {

    private final List<LogType> up;
    private final List<LogType> down;

    public BridgeLog() {
        this.up = new ArrayList<>();
        this.down = new ArrayList<>();
    }

    public void addLog(MovingCommand movingCommand, boolean isSame) {
        if (isSame) {
            addPass(movingCommand);
        }
        if (!isSame) {
            addFail(movingCommand);
        }
    }

    private void addPass(MovingCommand movingCommand) {
        if (movingCommand == MovingCommand.U) {
            up.add(LogType.PASS);
            down.add(LogType.NONE);
        }
        if (movingCommand == MovingCommand.D) {
            up.add(LogType.NONE);
            down.add(LogType.PASS);
        }
    }

    private void addFail(MovingCommand movingCommand) {
        if (movingCommand == MovingCommand.U) {
            up.add(LogType.FAIL);
            down.add(LogType.NONE);
        }
        if (movingCommand == MovingCommand.D) {
            up.add(LogType.NONE);
            down.add(LogType.FAIL);
        }
    }

    public void clear() {
        up.clear();
        down.clear();
    }

    public List<LogType> getUp() {
        return up;
    }

    public List<LogType> getDown() {
        return down;
    }
}
