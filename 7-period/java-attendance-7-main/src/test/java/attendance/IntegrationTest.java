package attendance;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {

    @Test
    void 중복_출석_확인_테스트() {
        assertNowTest(
                () -> assertThatThrownBy(() -> run("1", "쿠키", "10:08"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR]"),
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 운영시간_외_출석_확인_테스트1() {
        assertNowTest(
                () -> assertThatThrownBy(() -> run("1", "쿠키", "23:01"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR]"),
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 운영시간_외_출석_확인_테스트2() {
        assertNowTest(
                () -> assertThatThrownBy(() -> run("1", "쿠키", "07:59"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR]"),
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
