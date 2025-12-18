package vendingmachine;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInListTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                            "투입 금액: 3000원", "투입 금액: 1500원"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @Test
    void 기능_테스트2() {
        assertRandomNumberInListTest(
                () -> {
                    run("800", "[콜라,1500,20];[사이다,1000,10]", "1200", "사이다");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 1개", "100원 - 2개", "50원 - 1개", "10원 - 5개",
                            "투입 금액: 1200원", "100원 - 2개"
                    );
                },
                500, 100, 100, 50, 10, 10, 10, 10, 10
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(
                () -> {
                    runException("-1");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 상품_가격_시작범위_예외_테스트() {
        assertSimpleTest(() -> {
            runException("400", "[콜라,10,20]");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 상품_가격_유닛_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("400", "[콜라,115,20]");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 잔액_부족으로_구매_불가_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20]", "1000", "콜라");
                    assertThat(output()).contains(
                            "잔돈",
                            "100원 - 4개",
                            "50원 - 1개"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @Test
    void 상품_재고_없음_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,1]", "3000", "콜라", "콜라");
                    assertThat(output()).contains(
                            "잔돈",
                            "100원 - 4개",
                            "50원 - 1개"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @Test
    void 잘못된_상품_형식_입력_테스트() {
        assertSimpleTest(() -> {
            runException("450", "[콜라,1500]");  // 수량 누락
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 잘못된_투입_금액_입력_테스트() {
        assertSimpleTest(() -> {
            runException("450", "[콜라,1500,20]", "-1000");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 존재하지_않는_상품_구매_시도_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20]", "2000", "사이다", "콜라");
                    assertThat(output()).contains(ERROR_MESSAGE);
                },
                100, 100, 100, 100, 50
        );
    }

    @Test
    void 잔돈_반환_불가능_케이스_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20]", "2000", "콜라");
                    assertThat(output()).contains(
                            "100원 - 4개",
                            "50원 - 1개"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
