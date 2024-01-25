package org.ygcxy.utils;

import java.util.Random;

/**
 * @Project: org.ygcxy.utils
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:50
 * @Description:
 */
public class RandomStringUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
