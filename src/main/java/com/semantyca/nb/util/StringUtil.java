package com.semantyca.nb.util;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtil {

    public static String encodeSHA256(String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }

    public static String getRndText() {
        return getRndText("qwertyuiopasdfghjklzxcvbnm", 10);
    }

    public static String getRndText(int len) {
        return getRndText("qwertyuiopasdfghjklzxcvbnm", len);
    }

    public static String getRndText(String setOfTheLetters, int len) {
        Random r = new Random();
        String key = "";
        char[] letters = new char[setOfTheLetters.length() + 10];

        for (int i = 0; i < 10; i++) {
            letters[i] = Character.forDigit(i, 10);
        }

        for (int i = 0; i < setOfTheLetters.length(); i++) {
            letters[i + 10] = setOfTheLetters.charAt(i);
        }

        for (int i = 0; i < len; i++) {
            key += letters[Math.abs(r.nextInt()) % letters.length];
        }

        return key;
    }
}
