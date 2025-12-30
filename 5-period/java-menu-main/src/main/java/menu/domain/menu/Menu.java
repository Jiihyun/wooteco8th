package menu.domain.menu;

import java.util.Arrays;
import menu.exception.ExceptionMessage;

public enum Menu {

    규동("규동", MenuCategory.JAPANESE),
    우동("우동", MenuCategory.JAPANESE),
    미소시루("미소시루", MenuCategory.JAPANESE),
    스시("스시", MenuCategory.JAPANESE),
    가츠동("가츠동", MenuCategory.JAPANESE),
    오니기리("오니기리", MenuCategory.JAPANESE),
    하이라이스("하이라이스", MenuCategory.JAPANESE),
    라멘("라멘", MenuCategory.JAPANESE),
    오코노미야끼("오코노미야끼", MenuCategory.JAPANESE),

    김밥("김밥", MenuCategory.KOREAN),
    김치찌개("김치찌개", MenuCategory.KOREAN),
    쌈밥("쌈밥", MenuCategory.KOREAN),
    된장찌개("된장찌개", MenuCategory.KOREAN),
    비빔밥("비빔밥", MenuCategory.KOREAN),
    칼국수("칼국수", MenuCategory.KOREAN),
    불고기("불고기", MenuCategory.KOREAN),
    떡볶이("떡볶이", MenuCategory.KOREAN),
    제육볶음("제육볶음", MenuCategory.KOREAN),

    깐풍기("깐풍기", MenuCategory.CHINESE),
    볶음면("볶음면", MenuCategory.CHINESE),
    동파육("동파육", MenuCategory.CHINESE),
    짜장면("짜장면", MenuCategory.CHINESE),
    짬뽕("짬뽕", MenuCategory.CHINESE),
    마파두부("마파두부", MenuCategory.CHINESE),
    탕수육("탕수육", MenuCategory.CHINESE),
    토마토_달걀볶음("토마토 달걀볶음", MenuCategory.CHINESE),
    고추잡채("고추잡채", MenuCategory.CHINESE),

    팟타이("팟타이", MenuCategory.ASIAN),
    카오_팟("카오 팟", MenuCategory.ASIAN),
    나시고렝("나시고렝", MenuCategory.ASIAN),
    파인애플_볶음밥("파인애플 볶음밥", MenuCategory.ASIAN),
    쌀국수("쌀국수", MenuCategory.ASIAN),
    똠얌꿍("똠얌꿍", MenuCategory.ASIAN),
    반미("반미", MenuCategory.ASIAN),
    월남쌈("월남쌈", MenuCategory.ASIAN),
    분짜("분짜", MenuCategory.ASIAN),

    라자냐("라자냐", MenuCategory.AMERICAN),
    그라탱("그라탱", MenuCategory.AMERICAN),
    뇨끼("뇨끼", MenuCategory.AMERICAN),
    끼슈("끼슈", MenuCategory.AMERICAN),
    프렌치_토스트("프렌치 토스트", MenuCategory.AMERICAN),
    바게트("바게트", MenuCategory.AMERICAN),
    스파게티("스파게티", MenuCategory.AMERICAN),
    피자("피자", MenuCategory.AMERICAN),
    파니니("파니니", MenuCategory.AMERICAN),
    ;
    
    private final String name;
    private final MenuCategory menuCategory;

    Menu(String name, MenuCategory menuCategory) {
        this.name = name;
        this.menuCategory = menuCategory;
    }

    public static Menu from(String input) {
        return Arrays.stream(Menu.values())
                .filter(element -> element.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MENU_NOT_EXISTS.getMessage()));
    }
}
