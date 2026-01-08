package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭 : 한나");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭", "한나")
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

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
