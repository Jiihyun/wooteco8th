package subway.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.command.SearchCommand;
import subway.dto.SearchedResult;
import subway.exception.ExceptionMessage;

public class PathSearchingMachine {

    private final SectionInfos sectionInfos;

    public PathSearchingMachine(SectionInfos sectionInfos) {
        this.sectionInfos = sectionInfos;
    }

    public SearchedResult search(Station startStation, Station endStation, SearchCommand command) {
        validateStation(startStation, endStation);
        if (command == SearchCommand.최단_거리) {
            return searchShortestPath(initDistanceGraph(), startStation, endStation, true);
        }
        return searchShortestPath(initTimeGraph(), startStation, endStation, false);
    }

    private void validateStation(Station startStation, Station endStation) {
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_STATION.getMessage());
        }
    }

    private SearchedResult searchShortestPath(WeightedMultigraph<String, DefaultWeightedEdge> graph,
                                              Station startStation, Station endStation, boolean byDistance) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultWeightedEdge> path = dijkstra.getPath(startStation.getName(), endStation.getName());
        List<String> vertexList = path.getVertexList();

        if (byDistance) {
            int distance = (int) path.getWeight();
            int time = sectionInfos.calculateTimes(vertexList);
            return new SearchedResult(distance, time, vertexList);
        }
        int time = (int) path.getWeight();
        int distance = sectionInfos.calculateDistances(vertexList);
        return new SearchedResult(distance, time, vertexList);
    }

    private WeightedMultigraph<String, DefaultWeightedEdge> initDistanceGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        addSectionInfo();
        addStation(graph);
        for (SectionInfo sectionInfo : sectionInfos.getSectionInfos()) {
            setEdgeWeight(graph, sectionInfo.getStations(), sectionInfo.getDistance());
        }
        return graph;
    }

    private WeightedMultigraph<String, DefaultWeightedEdge> initTimeGraph() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        addSectionInfo();
        addStation(graph);
        for (SectionInfo sectionInfo : sectionInfos.getSectionInfos()) {
            setEdgeWeight(graph, sectionInfo.getStations(), sectionInfo.getTime());
        }
        return graph;
    }

    private void addStation(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        for (Station station : StationRepository.findAll()) {
            graph.addVertex(station.getName());
        }
    }

    private void setEdgeWeight(WeightedMultigraph<String, DefaultWeightedEdge> graph,
                               Set<String> stations, int weight) {
        List<String> startAndEnd = stations.stream().toList();
        graph.setEdgeWeight(graph.addEdge(startAndEnd.getFirst(), startAndEnd.getLast()), weight);
    }

    public void addSectionInfo() {
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("교대역", "강남역")), 2, 3));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("강남역", "역삼역")), 2, 3));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("교대역", "남부터미널역")), 3, 2));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("남부터미널역", "양재역")), 6, 5));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("양재역", "매봉역")), 1, 1));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("강남역", "양재역")), 2, 8));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("양재역", "양재시민의숲역")), 10, 3));
    }
}
