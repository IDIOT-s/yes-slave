package org.idiot.yesslave.worktimer.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationCode {

    private static final int CAPACITY = 6;

    private String verificationCode;

    private VerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * 확인코드를 생성합니다.
     */
    public static VerificationCode create(AuthenticateCodeGenerator strategy) {
        return new VerificationCode(strategy.create(CAPACITY));
    }

    public static VerificationCode manual(String verificationCode) {
        return new VerificationCode(verificationCode);
    }
}
