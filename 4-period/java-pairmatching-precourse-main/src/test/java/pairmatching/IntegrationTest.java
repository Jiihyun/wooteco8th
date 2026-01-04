package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 홀수_인원_페어_매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 대만");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "대만")
        );
    }

    @Test
    void 이미_존재하는_페어_재매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "네",
                            "2", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
                    assertThat(output()).contains("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                            + "네 | 아니오");
                    assertThat(output()).contains("치수 : 태섭", "태웅 : 백호");
                    assertThat(output()).contains("치수 : 태섭", "태웅 : 백호");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭"),
                Arrays.asList("치수", "태섭", "태웅", "백호")
        );
    }

    @Test
    void 매칭_실패_3번_초과() {
        assertShuffleTest(
                () -> {
                    runException("1", "프론트엔드, 레벨1, 자동차경주", "1", "프론트엔드, 레벨1, 로또");
                    assertThat(output()).contains("보노 : 덴버", "시저 : 신디", "쉐리 : 다비");
                    assertThat(output()).contains(ERROR_MESSAGE);

                },
                Arrays.asList("보노", "덴버", "시저", "신디", "쉐리", "다비"),
                Arrays.asList("신디", "시저", "보노", "다비", "덴버", "쉐리")
        );
    }

    @Test
    void 없는_매칭_조회에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("2", "백엔드, 레벨1, 자돋차경주");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 없는_레벨에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨6, 오징어게임");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 해당_레벨에_없는_미션에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 장바구니");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 없는_명령어에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("4");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 재매칭_대답에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "넵");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
