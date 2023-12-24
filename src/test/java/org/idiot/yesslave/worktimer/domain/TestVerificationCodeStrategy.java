package org.idiot.yesslave.worktimer.domain;

import java.util.List;


public class TestVerificationCodeStrategy implements AuthenticateCodeGenerator {
    private final List<String> numbers;
    public TestVerificationCodeStrategy(List<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String create() {
        String code = numbers.get(0);
        numbers.remove(0);
        return code;
    }
}
