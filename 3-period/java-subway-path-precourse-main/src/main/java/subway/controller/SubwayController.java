package subway.controller;

import subway.DataInitializer;
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
        DataInitializer.init();
        while (keepProgram()) {
            readSearchCommand();
        }
    }

    private boolean keepProgram() {
        MainCommand mainCommand = RetryHandler.retryOnInvalidInput(inputView::readMainCommand);
        return mainCommand != MainCommand.QUIT;
    }

    private void readSearchCommand() {
        SearchCommand searchCommand = RetryHandler.retryOnInvalidInput(inputView::readSearchCommand);
        if (searchCommand == SearchCommand.BACK) {
            return;
        }
        RetryHandler.retryOnInvalidInput(() -> search(searchCommand));
    }

    private void search(SearchCommand searchCommand) {
        PathSearchingMachine pathSearchingMachine = new PathSearchingMachine(new SectionInfos());

        Station startStation = RetryHandler.retryOnInvalidInput(() -> StationRepository.findByName(inputView.readStartStation()));
        Station endStation = RetryHandler.retryOnInvalidInput(() -> StationRepository.findByName(inputView.readEndStation()));

        SearchedResult searchedResult = pathSearchingMachine.search(startStation, endStation, searchCommand);
        OutputView.showSearchedResult(searchedResult);
    }
}
