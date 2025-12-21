package bridge.domain;

public enum LogType {

    PASS("O"),
    FAIL("X"),
    NONE(" "),
    ;

    private final String shape;

    LogType(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
