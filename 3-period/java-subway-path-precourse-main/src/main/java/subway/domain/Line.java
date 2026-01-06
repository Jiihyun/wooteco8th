package subway.domain;

import java.util.Objects;

public class Line {
    private String name;

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Line line)) {
            return false;
        }

        return Objects.equals(getName(), line.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
