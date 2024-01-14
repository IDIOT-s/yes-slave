package org.idiot.yesslave.test.fixture;

import java.util.Random;

public class TestFixtureUtils {
    private static final Random random = new Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static Long size에_맞는_Long_생성(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("크기는 음수일 수 없습니다.");
        }

        return (long) (random.nextDouble() * (size + 1));
    }

    public static String size에_맞는_문자열_생성(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("크기는 음수일 수 없습니다.");
        }

        if (size > 1024) {
            throw new IllegalArgumentException("크기는 1024보다 작아야 합니다.");
        }

        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
