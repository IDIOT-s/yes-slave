package org.idiot.yesslave.worktimer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VerificationCodeTest {
    @Test
    @DisplayName("6자리 영어, 숫자가 섞인 난수를 생성합니다.")
    void success() {
        //given
        final int expectedLength = 6;
        final AuthenticateCodeGenerator generator = new RandomCodeGenerator();

        //when
        VerificationCode result = VerificationCode.create(generator);

        //then
        assertThat(result.getVerificationCode().length()).isEqualTo(expectedLength);
    }
}
