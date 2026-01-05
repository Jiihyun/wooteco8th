package attendance.domain;

public class Nickname {

    private final String value;

    public Nickname(String value) {
        this.value = value;
    }

    public boolean hasSameValue(String value) {
        return this.value.equals(value);
    }

    public String getValue() {
        return value;
    }
}
