package pairmatching.domain;

import java.util.Objects;

public class Crew {

    private final String name;

    public Crew(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Crew crew)) {
            return false;
        }
        return Objects.equals(getName(), crew.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
