package subway.domain;

import java.util.Set;

public class SectionInfo {

    private final Set<String> stations;
    private final int distance;
    private final int time;

    public SectionInfo(Set<String> stations, int distance, int time) {
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public Set<String> getStations() {
        return stations;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
