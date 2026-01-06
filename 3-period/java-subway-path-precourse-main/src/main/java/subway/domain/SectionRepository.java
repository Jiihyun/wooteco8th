package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SectionRepository {
    private static final List<Section> sections = new ArrayList<>();

    public static List<Section> stations() {
        return Collections.unmodifiableList(sections);
    }

    public static void addSection(Section section) {
        sections.add(section);
    }

    public static boolean deleteSection(String line, String station) {
        return sections.removeIf(section -> Objects.equals(section.getLine().getName(), line)
                && Objects.equals(section.getStation(), station));
    }

    public static void deleteAll() {
        sections.clear();
    }
}
