package subway.domain;

import java.util.HashSet;
import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.dto.SearchedResult;
import subway.exception.ExceptionMessage;

public class PathSearchingMachine {

    private final SectionInfos sectionInfos;

    public PathSearchingMachine(SectionInfos sectionInfos) {
        this.sectionInfos = sectionInfos;
    }

    public SearchedResult search(Station startStation, Station endStation, String command) {
        validateStation(startStation, endStation);
        addSectionInfo();
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        if (command.equals("1")) {
            initByDistance(graph);
        }
        if (command.equals("2")) {
            initByTime(graph);
        }
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        GraphPath path = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName());
        int distance = (int) path.getWeight();
        List vertexList = path.getVertexList();
        if (command.equals("1")) {
            int time = sectionInfos.calculateTimes(vertexList);
            return new SearchedResult(distance, time, vertexList);
        }
        int dis = sectionInfos.calculateDistances(vertexList);
        return new SearchedResult(distance, dis, vertexList);
    }

    private void initByDistance(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        addStation(graph);
        graph.setEdgeWeight(graph.addEdge("교대역", "강남역"), 2);
        graph.setEdgeWeight(graph.addEdge("강남역", "역삼역"), 2);
        graph.setEdgeWeight(graph.addEdge("교대역", "남부터미널역"), 3);
        graph.setEdgeWeight(graph.addEdge("남부터미널역", "양재역"), 6);
        graph.setEdgeWeight(graph.addEdge("양재역", "매봉역"), 1);
        graph.setEdgeWeight(graph.addEdge("강남역", "양재역"), 2);
        graph.setEdgeWeight(graph.addEdge("양재역", "양재시민의숲역"), 10);
    }

    private void initByTime(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        addStation(graph);
        graph.setEdgeWeight(graph.addEdge("교대역", "강남역"), 3);
        graph.setEdgeWeight(graph.addEdge("강남역", "역삼역"), 3);
        graph.setEdgeWeight(graph.addEdge("교대역", "남부터미널역"), 1);
        graph.setEdgeWeight(graph.addEdge("남부터미널역", "양재역"), 5);
        graph.setEdgeWeight(graph.addEdge("양재역", "매봉역"), 1);
        graph.setEdgeWeight(graph.addEdge("강남역", "양재역"), 8);
        graph.setEdgeWeight(graph.addEdge("양재역", "양재시민의숲역"), 3);
    }

    private void addStation(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        for (Station station : StationRepository.findAll()) {
            graph.addVertex(station.getName());
        }
    }

    private void validateStation(Station startStation, Station endStation) {
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_STATION.getMessage());
        }
    }

    public void addSectionInfo() {
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("교대역", "강남역")), 2, 3));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("강남역", "역삼역")), 2, 3));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("교대역", "남부터미널역")), 3, 1));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("남부터미널역", "양재역")), 6, 5));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("양재역", "매봉역")), 1, 1));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("강남역", "양재역")), 2, 8));
        sectionInfos.add(new SectionInfo(new HashSet<>(List.of("양재역", "양재시민의숲역")), 10, 3));
    }
}
