package org.idiot.yesslave.test.fixture.test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomUtils;
import org.idiot.yesslave.test.fixture.TestFixtureUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Disabled("비즈니스와 상관 없는 Test Util에 관한 테스트로 생략. 관련 코드가 수정 되면 로컬에서 검증하기로")
public class TestFixtureUtilsTest {

    @ParameterizedTest(name = "size : {0}")
    @MethodSource("랜덤_size_생성")
    @DisplayName("랜덤으로 0에서 size 사이의 Long을 생성")
    void makeRandomLong(Integer size) {
        //when
        Long result = TestFixtureUtils.size에_맞는_Long_생성(size);

        //then
        assertSoftly(it -> {
            it.assertThat(result).isGreaterThanOrEqualTo(0);
            it.assertThat(result).isLessThanOrEqualTo(size);
        });
    }

    @ParameterizedTest(name = "size : {0}")
    @MethodSource("랜덤_1024_생성")
    @DisplayName("랜덤으로 0에서 size 사이의 String을 생성")
    void makeRandomString(Integer size) {
        //when
        String result = TestFixtureUtils.size에_맞는_문자열_생성(size);

        //then
        assertSoftly(it -> {
            it.assertThat(result).hasSizeGreaterThanOrEqualTo(0);
            it.assertThat(result).hasSizeLessThanOrEqualTo(size);
        });
    }

    public static Stream<Integer> 랜덤_1024_생성() {
        return IntStream.range(0, 10)
            .boxed()
            .map((it) -> RandomUtils.nextInt(0, 1024));
    }

    public static Stream<Integer> 랜덤_size_생성() {
        return IntStream.range(0, 10)
            .boxed()
            .map((it) -> RandomUtils.nextInt(0, Integer.MAX_VALUE));
    }
}
