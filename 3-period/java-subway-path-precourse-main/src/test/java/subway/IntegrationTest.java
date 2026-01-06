package subway;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 최단_경로_조회() {
        assertSimpleTest(() -> {
            run("1", "1", "교대역", "양재역", "Q");
            assertThat(output()).contains(
                    "[INFO] 총 거리: 4km",
                    "[INFO] 총 소요 시간: 11분",
                    "[INFO] 교대역",
                    "[INFO] 강남역",
                    "[INFO] 양재역"
            );
        });
    }

    @Test
    void 최소_시간_조회() {
        assertSimpleTest(() -> {
            run("1", "2", "강남역", "남부터미널역", "Q");
            assertThat(output()).contains(
                    "[INFO] 총 거리: 5km",
                    "[INFO] 총 소요 시간: 5분",
                    "[INFO] 강남역",
                    "[INFO] 교대역",
                    "[INFO] 남부터미널역"
            );
        });
    }

    @Test
    void 출발역_도착역_같은_경우_예외_발생() {
        assertSimpleTest(() -> {
            run("1", "2", "강남역", "강남역",
                    "강남역", "남부터미널역", "Q");
            assertThat(output()).contains(
                    "[INFO] 총 거리: 5km",
                    "[INFO] 총 소요 시간: 5분",
                    "[INFO] 강남역",
                    "[INFO] 교대역",
                    "[INFO] 남부터미널역"
            );
        });
    }

    @Test
    void 잘못된_경로_기준_선택_오류() {
        assertSimpleTest(() -> {
            runException("1", "3");
            assertThat(output()).contains(
                    ERROR_MESSAGE
            );
        });
    }

    @Test
    void 메인_메뉴로_돌아가기_성공() {
        assertSimpleTest(() -> {
            run("1", "B", "Q");
            assertThat(output()).contains(
                    "## 메인 화면",
                    "1. 경로 조회",
                    "Q. 종료"
            );
        });
    }

    @Test
    void 존재하지_않는_역_입력_오류() {
        assertSimpleTest(() -> {
            runException("1", "1", "없는역", "강남역");
            assertThat(output()).contains(
                    ERROR_MESSAGE
            );
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
