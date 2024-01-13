package org.idiot.yesslave.worktimer.domain;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCodeGenerator implements AuthenticateCodeGenerator {
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random random = new SecureRandom();

    @Override
    public String create(int capacity) {
        StringBuilder sb = new StringBuilder(capacity);

        for (int i = 0; i < capacity; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
