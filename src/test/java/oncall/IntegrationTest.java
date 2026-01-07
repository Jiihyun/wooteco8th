package oncall;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 인원_미만_예외_테스트() {
        assertSimpleTest(() -> {
            runException("4,토",
                    "허브, 쥬니, 말랑");
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    void 인원_초과_예외_테스트() {
        assertSimpleTest(() -> {
            runException("4,토",
                    "4,토",
                    "허브,쥬니,말랑,라온,헤나,우코,에단,수달,파워,히이로,마코,슬링키,모디,연어,깃짱,리오,고니,박스터,달리,조이,노아이즈,도이,도치,홍고,스캇,폴로,해시,로지,첵스,아이크,우가,푸만능,애쉬,로이스,오션",
                    "오션,로이스,애쉬,푸만능,우가,아이크,첵스,로지,해시,폴로,스캇,홍고,도치,도이,노아이즈,조이,달리,박스터,고니,리오,깃짱,연어,모디,슬링키,마코,히이로,파워,수달,에단,우코,헤나,라온,말랑,쥬니,허브,러키"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    void 중복_근무_예외_테스트() {
        assertSimpleTest(() -> {
            runException("4,토",
                    "허브,쥬니,말랑,허브,헤나"
            );
            assertThat(output()).contains("[ERROR]");
        });
    }

    @Test
    void 휴일_잘못된_입력_이후_평일_입력_순서_테스트() {
        assertSimpleTest(() -> {
            runException("4,토",
                    "허브,쥬니,말랑,다코,헤나",
                    "쥬니,쥬니,말랑,허브,헤나",
                    "허브,쥬니,말랑,다코,헤나",
                    "말랑,쥬니,허브,다코,헤나"
            );
            assertThat(output()).contains("[ERROR]",
                    "4월 1일 토 말랑" + LINE_SEPARATOR,
                    "4월 2일 일 쥬니" + LINE_SEPARATOR,
                    "4월 3일 월 허브" + LINE_SEPARATOR,
                    "4월 4일 화 쥬니" + LINE_SEPARATOR,
                    "4월 5일 수 말랑" + LINE_SEPARATOR)
            ;
        });
    }

    @Test
    void 기능_테스트() {
        assertSimpleTest(() -> {
            run(
                    "5,월",
                    "준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리",
                    "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니"
            );
            assertThat(output()).contains(
                    "5월 1일 월 준팍\n" +
                            "5월 2일 화 도밥\n" +
                            "5월 3일 수 고니\n" +
                            "5월 4일 목 수아\n" +
                            "5월 5일 금(휴일) 루루\n" +
                            "5월 6일 토 수아\n" +
                            "5월 7일 일 글로\n" +
                            "5월 8일 월 루루\n" +
                            "5월 9일 화 글로\n" +
                            "5월 10일 수 솔로스타\n" +
                            "5월 11일 목 우코\n" +
                            "5월 12일 금 슬링키\n" +
                            "5월 13일 토 솔로스타\n" +
                            "5월 14일 일 우코\n" +
                            "5월 15일 월 참새\n" +
                            "5월 16일 화 도리\n" +
                            "5월 17일 수 준팍\n" +
                            "5월 18일 목 도밥\n" +
                            "5월 19일 금 고니\n" +
                            "5월 20일 토 슬링키\n" +
                            "5월 21일 일 참새\n" +
                            "5월 22일 월 수아\n" +
                            "5월 23일 화 루루\n" +
                            "5월 24일 수 글로\n" +
                            "5월 25일 목 솔로스타\n" +
                            "5월 26일 금 우코\n" +
                            "5월 27일 토 도리\n" +
                            "5월 28일 일 준팍\n" +
                            "5월 29일 월 슬링키\n" +
                            "5월 30일 화 참새\n" +
                            "5월 31일 수 도리"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
