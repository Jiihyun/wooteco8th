package menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mockStatic;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;

public class CustomApplicationTest extends NsTest {

    private static final Duration RANDOM_TEST_TIMEOUT = Duration.ofSeconds(10L);

    @DisplayName("전체 기능 테스트")
    @Nested
    class AllFeatureTest {
        
        @Test
        void 이름_길이_예외_테스트() {
            assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
                runException("구,제임스");
                assertThat(output()).contains("[ERROR]");
            });
        }

        @Test
        void 이름_중복_예외_테스트() {
            assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
                runException("제임스,제임스");
                assertThat(output()).contains("[ERROR]");
            });
        }

        @Test
        void 총인원_미만_예외_테스트() {
            assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
                runException("제임스");
                assertThat(output()).contains("[ERROR]");
            });
        }

        @Test
        void 총인원_초과_예외_테스트() {
            assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
                runException("제임스,구,가가,메메,우이,도롱");
                assertThat(output()).contains("[ERROR]");
            });
        }
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    private static void assertRandomTest(
            final Executable executable,
            final Mocking... mockings
    ) {
        assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
            try (final MockedStatic<Randoms> mock = mockStatic(Randoms.class)) {
                Arrays.stream(mockings).forEach(mocking -> mocking.stub(mock));
                executable.execute();
            }
        });
    }

    public static class Mocking<T> {

        /**
         * stubbing lambda verification 예시) () -> Randoms.pickNumberInList(anyList())
         */
        private final MockedStatic.Verification verification;

        // 반환할 첫 번째 값
        private final T value;

        /**
         * 첫 번째 값을 반환하고 나서 다음에 반환할 값들. 예를 들면, verification을 처음 실행하면 value를 반환하고 두 번째 실행하면 values[0]을
         * 반환한다.
         */
        private final T[] values;

        private Mocking(final MockedStatic.Verification verification,
                        final T value,
                        final T... values) {
            this.verification = verification;
            this.value = value;
            this.values = values;
        }

        public static Mocking ofRandomNumberInRange(final Integer value, final Integer... values) {
            return new Mocking(() -> Randoms.pickNumberInRange(anyInt(), anyInt()), value, values);
        }

        public static <T> Mocking ofShuffle(final List<T> value, final List<T>... values) {
            return new Mocking(() -> Randoms.shuffle(anyList()), value, values);
        }

        public <S> void stub(final MockedStatic<S> mock) {
            mock.when(verification).thenReturn(value, Arrays.stream(values).toArray());
        }
    }
}
