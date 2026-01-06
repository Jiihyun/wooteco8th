package attendance;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.LocalDate;
import org.junit.jupiter.api.Nested;
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

    @Test
    void 제적_위험자_확인_기능_테스트() {
        assertNowTest(
                () -> {
                    runException("4");
                    assertThat(output()).contains(
                            "제적 위험자 조회 결과",

                            "- 빙티: 결석 3회, 지각 3회 (면담)",
                            "- 이든: 결석 2회, 지각 4회 (면담)",
                            "- 쿠키: 결석 2회, 지각 2회 (경고)",
                            "- 빙봉: 결석 1회, 지각 5회 (경고)"
                    );
                },
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 출석_중복_테스트() {
        assertNowTest(
                () -> assertThatThrownBy(() -> run("1", "빙봉", "09:03", "1", "빙봉", "10:03"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Test
    void 출석_확인_기능_테스트() {
        assertNowTest(
                () -> {
                    runException("1", "짱수", "08:00");
                    assertThat(output()).contains("12월 13일 금요일 08:00 (출석)");
                },
                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }

    @Nested
    class 문제_요구사항_예시 {

        @Test
        void 기능1_테스트() {
            assertNowTest(
                    () -> {
                        runException("1", "짱수", "09:59");
                        assertThat(output()).contains(
                                "오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.",
                                "닉네임을 입력해 주세요.",
                                "등교 시간을 입력해 주세요.",
                                "12월 13일 금요일 09:59 (출석)"
                        );
                    },
                    LocalDate.of(2024, 12, 13).atStartOfDay()
            );
        }

        @Test
        void 기능2_테스트() {
            assertNowTest(
                    () -> {
                        runException("2", "빙티", "3", "09:58");
                        assertThat(output()).contains(
                                "오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.",
                                "출석을 수정하려는 크루의 닉네임을 입력해 주세요.",
                                "수정하려는 날짜(일)를 입력해 주세요.",
                                "언제로 변경하겠습니까?",
                                "12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!"
                        );
                    },
                    LocalDate.of(2024, 12, 13).atStartOfDay()
            );
        }

        @Test
        void 기능3_테스트() {
            assertNowTest(
                    () -> {
                        runException("2", "빙티", "3", "09:58", "3", "빙티");
                        assertThat(output()).contains(
                                "이번 달 빙티의 출석 기록입니다.",
                                "12월 02일 월요일 13:00 (출석)",
                                "12월 03일 화요일 09:58 (출석)",
                                "12월 04일 수요일 10:02 (출석)",
                                "12월 05일 목요일 10:06 (지각)",
                                "12월 06일 금요일 10:01 (출석)",
                                "12월 09일 월요일 --:-- (결석)",
                                "12월 10일 화요일 10:08 (지각)",
                                "12월 11일 수요일 --:-- (결석)",
                                "12월 12일 목요일 --:-- (결석)",
                                "출석: 4회",
                                "지각: 2회",
                                "결석: 3회",
                                "면담 대상자입니다."
                        );
                    },
                    LocalDate.of(2024, 12, 13).atStartOfDay()
            );
        }

        @Test
        void 기능4_테스트() {
            assertNowTest(
                    () -> {
                        runException("2", "빙티", "3", "09:58", "4");
                        assertThat(output()).contains(
                                "제적 위험자 조회 결과",
                                "- 빙티: 결석 3회, 지각 2회 (면담)",
                                "- 이든: 결석 2회, 지각 4회 (면담)",
                                "- 쿠키: 결석 2회, 지각 2회 (경고)",
                                "- 빙봉: 결석 1회, 지각 5회 (경고)"
                        );
                    },
                    LocalDate.of(2024, 12, 13).atStartOfDay()
            );
        }
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
