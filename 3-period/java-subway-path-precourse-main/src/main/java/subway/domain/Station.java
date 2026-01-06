package subway.domain;

import java.util.Objects;

public class Station {
    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Station station)) {
            return false;
        }

        return Objects.equals(getName(), station.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
