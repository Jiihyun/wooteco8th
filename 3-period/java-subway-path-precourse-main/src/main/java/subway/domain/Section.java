package subway.domain;

public class Section {

    private final Station station;
    private final Line line;

    public Section(Station station, Line line) {
        this.station = station;
        this.line = line;
    }

    public Station getStation() {
        return station;
    }

    public Line getLine() {
        return line;
    }
}
