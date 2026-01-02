package oncall;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 요일_예외_테스트() {
        assertSimpleTest(() -> {
            run("1,열",
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 날짜_범위_예외_테스트() {
        assertSimpleTest(() -> {
            run("13,일",
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 순번_중복_예외_테스트() {
        assertSimpleTest(() -> {
            run("4,토",
                    "허브,쥬니,말랑,허브,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 닉네임_길이_예외_테스트() {
        assertSimpleTest(() -> {
            run("4,토",
                    "허브,쥬니,말랑,허브허브허브,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 근무자_범위_예외_테스트() {
        assertSimpleTest(() -> {
            run("4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브"
            );
            assertThat(output()).contains(
                    "[ERROR]",
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 로이스" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 연속_근무시_순서_변경_테스트() {
        assertSimpleTest(() -> {
            run("4,토",
                    "허브,쥬니,말랑,라온,헤나",
                    "오션,허브,로이스,애쉬,푸만능"
            );
            assertThat(output()).contains(
                    "4월 1일 토 오션" + LINE_SEPARATOR,
                    "4월 2일 일 허브" + LINE_SEPARATOR,
                    "4월 3일 월 쥬니" + LINE_SEPARATOR,
                    "4월 4일 화 허브" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR,
                    "4월 6일 목 라온" + LINE_SEPARATOR,
                    "4월 7일 금 헤나" + LINE_SEPARATOR,

                    "4월 8일 토 로이스" + LINE_SEPARATOR,
                    "4월 9일 일 애쉬" + LINE_SEPARATOR,
                    "4월 10일 월 쥬니" + LINE_SEPARATOR,
                    "4월 11일 화 허브" + LINE_SEPARATOR
            );
        });
    }

    @Test
    void 평일_휴일_근무시_휴일_포함_테스트() {
        assertSimpleTest(() -> {
            run("1,월",
                    "쥬니,허브,말랑,라온,헤나",
                    "오션,허브,로이스,애쉬,푸만능"
            );
            assertThat(output()).contains(
                    "1월 1일 월(휴일) 오션" + LINE_SEPARATOR,
                    "1월 2일 화 쥬니" + LINE_SEPARATOR,
                    "1월 3일 수 허브" + LINE_SEPARATOR,
                    "1월 4일 목 말랑" + LINE_SEPARATOR,
                    "1월 5일 금 라온" + LINE_SEPARATOR,
                    "1월 6일 토 허브" + LINE_SEPARATOR,
                    "1월 7일 일 로이스" + LINE_SEPARATOR
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
