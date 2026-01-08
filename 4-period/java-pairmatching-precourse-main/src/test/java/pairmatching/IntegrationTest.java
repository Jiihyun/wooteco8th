package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class IntegrationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 짝수_인원_페어_매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭", "한나 : 준호");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "한나", "준호")
        );
    }

    @Test
    void 매칭_불가_테스트() {
        assertShuffleTest(
                () -> assertThatThrownBy(() -> run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 로또"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ERROR_MESSAGE),

                Arrays.asList("태웅", "백호", "치수", "태섭", "한나"),
                Arrays.asList("백호", "태웅", "태섭", "한나", "치수"),
                Arrays.asList("백호", "태웅", "태섭", "한나", "치수"),
                Arrays.asList("백호", "태웅", "태섭", "한나", "치수")
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
                    assertThat(output()).contains("치수 : 태웅", "태섭 : 백호");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭"),
                Arrays.asList("치수", "태웅", "태섭", "백호")
        );
    }

    @Test
    void 없는_매칭_조회에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    assertThatThrownBy(() -> run("2", "백엔드, 레벨1, 자동차경주"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining(ERROR_MESSAGE);
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
                    runException("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "넹");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
