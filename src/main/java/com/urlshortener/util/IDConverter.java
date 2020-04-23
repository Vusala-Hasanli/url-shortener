package com.urlshortener.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class IDConverter {
    private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String createKeyword(Long id) {
        List<Integer> base62ID = convertBase10ToBase62ID(id);
        StringBuilder uniqueURLID = new StringBuilder();
        base62ID.forEach(digit->uniqueURLID.append(characters.charAt(digit)));

        return uniqueURLID.toString();
    }

    private static List<Integer> convertBase10ToBase62ID(Long id) {
        LinkedList<Integer> digits = new LinkedList<>();
        while(id > 0) {
            int remainder = (int)(id % 62);
            digits.addFirst(remainder);
            id /= 62;
        }
        return digits;
    }

    public static String generateRandomKeyword() {//second way
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[3];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16);
    }
}
