package attendance;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void 출석_수정_및_크루별_출석_기록_확인_기능_테스트() {
        assertNowTest(
                () -> {
                    runException("3", "짱수");
                    assertThat(output()).contains(
                            "12월 02일 월요일 13:00 (출석)",
                            "12월 03일 화요일 10:00 (출석)",
                            "12월 04일 수요일 10:00 (출석)",
                            "12월 05일 목요일 10:00 (출석)",
                            "12월 06일 금요일 10:00 (출석)",
                            "12월 09일 월요일 13:00 (출석)",
                            "12월 10일 화요일 10:00 (출석)",
                            "12월 11일 수요일 --:-- (결석)",
                            "12월 12일 목요일 10:00 (출석)",
                            "출석: 8회",
                            "지각: 0회",
                            "결석: 1회"
                    );
                },
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
