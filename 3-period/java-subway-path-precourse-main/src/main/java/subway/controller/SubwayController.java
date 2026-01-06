package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.SectionRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;

public class SubwayController {

    private final InputView inputView;

    public SubwayController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        addLine();
        addStation();
        addSection();
    }

    private void addLine() {
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("신분당선"));
    }

    private void addStation() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));
        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
        StationRepository.addStation(new Station("매봉역"));
    }

    private void addSection() {
        SectionRepository.addSection(createStation("교대역", "2호선"));
        SectionRepository.addSection(createStation("강남역", "2호선"));
        SectionRepository.addSection(createStation("역삼역", "2호선"));
        SectionRepository.addSection(createStation("교대역", "3호선"));
        SectionRepository.addSection(createStation("남부터미널역", "3호선"));
        SectionRepository.addSection(createStation("양재역", "3호선"));
        SectionRepository.addSection(createStation("매봉역", "3호선"));
        SectionRepository.addSection(createStation("강남역", "신분당선"));
        SectionRepository.addSection(createStation("양재역", "신분당선"));
        SectionRepository.addSection(createStation("양재시민의숲역", "신분당선"));
    }

    private Section createStation(String station, String line) {
        return new Section(StationRepository.findByName(station), LineRepository.findByName(line));
    }
}
