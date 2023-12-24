package org.idiot.yesslave.worktimer.domain;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;


@Component
public class RandomCodeGenerator implements AuthenticateCodeGenerator {
    private final String CHARACTERS;
    private final Random random;
    private final int CAPACITY;

    public RandomCodeGenerator(final String CHARACTERS, Random random, int length) {
        this.CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        this.random = new SecureRandom();
        this.CAPACITY = length;
    }

    @Override
    public String create() {
        StringBuilder sb = new StringBuilder(CAPACITY);

        for (int i = 0; i < CAPACITY; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
