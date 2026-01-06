package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class SectionInfos {

    private final List<SectionInfo> sectionInfos;

    public SectionInfos() {
        this.sectionInfos = new ArrayList<>();
    }

    public void add(SectionInfo sectionInfo) {
        sectionInfos.add(sectionInfo);
    }

    public int calculateDistances(List<String> stations) {
        int sum = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            String start = stations.get(i);
            String end = stations.get(i + 1);
            SectionInfo info = sectionInfos.stream()
                    .filter(sectionInfo -> sectionInfo.getStations().contains(start))
                    .filter(sectionInfo -> sectionInfo.getStations().contains(end))
                    .findAny()
                    .orElseThrow();
            sum += info.getDistance();
        }
        return sum;
    }

    public int calculateTimes(List<String> stations) {
        int sum = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            String start = stations.get(i);
            String end = stations.get(i + 1);
            SectionInfo info = sectionInfos.stream()
                    .filter(sectionInfo -> sectionInfo.getStations().contains(start))
                    .filter(sectionInfo -> sectionInfo.getStations().contains(end))
                    .findAny()
                    .orElseThrow();
            sum += info.getTime();
        }
        return sum;
    }

    public List<SectionInfo> getSectionInfos() {
        return sectionInfos;
    }
}
