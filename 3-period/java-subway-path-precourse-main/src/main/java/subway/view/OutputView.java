package subway.view;

import subway.dto.SearchedResult;

public final class OutputView {

    private static final String FORMAT = """
            
            ## 조회 결과
            [INFO] ---
            [INFO] 총 거리: %dkm
            [INFO] 총 소요 시간: %d분
            [INFO] ---""";
    private static final String STATION_FORMAT = "[INFO] %s";

    private OutputView() {
    }

    public static void showSearchedResult(SearchedResult search) {
        System.out.println(FORMAT.formatted(search.distance(), search.time()));
        for (String station : search.stations()) {
            System.out.println(STATION_FORMAT.formatted(station));
        }
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
