package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트2() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("3000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "3개를 구매했습니다.",
                            "[1, 2, 3, 4, 5, 7]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[1, 4, 8, 14, 22, 45]",
                            "3개 일치 (5,000원) - 0개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 1,000,000.0%입니다."
                    );
                },
                List.of(1, 2, 3, 4, 5, 7),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(1, 4, 8, 14, 22, 45)
        );
    }

    @Test
    void 보너스_번호_중복_테스트() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "6");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_중복_테스트() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,5", "6");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 당첨_번호_사이즈_미만_테스트() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5", "6");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
