package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.PathSearchingMachine;
import subway.domain.SectionInfos;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.command.MainCommand;
import subway.domain.command.SearchCommand;
import subway.dto.SearchedResult;
import subway.util.RetryHandler;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;

    public SubwayController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        init();
        while (true) {
            MainCommand mainCommand = RetryHandler.retryOnInvalidInput(inputView::readMainCommand);
            if (mainCommand == MainCommand.SEARCH) {
                search();
            }
            if (mainCommand == MainCommand.QUIT) {
                return;
            }
        }
    }

    private void search() {
        PathSearchingMachine pathSearchingMachine = new PathSearchingMachine(new SectionInfos());
        SearchCommand searchCommand = RetryHandler.retryOnInvalidInput(inputView::readSearchCommand);
        if (searchCommand == SearchCommand.BACK) {
            return;
        }
        Station startStation = RetryHandler.retryOnInvalidInput(() -> StationRepository.findByName(inputView.readStartStation()));
        Station endStation = RetryHandler.retryOnInvalidInput(() -> StationRepository.findByName(inputView.readEndStation()));

        SearchedResult searchedResult = pathSearchingMachine.search(startStation, endStation, searchCommand);
        OutputView.showSearchedResult(searchedResult);
    }

    private void init() {
        addLine();
        addStation();
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
}
