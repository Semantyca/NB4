package com.semantyca.nb.util;

import com.semantyca.nb.logger.Lg;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class StringUtil {

    public static String encode(String val) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(val.getBytes(), 0, val.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            Lg.exception(e);
        }
        return null;
    }

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

    public static String getRndColor() {
        return (String) ListUtil.getRndElement(mColors);
    }


    private static String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7",  // light gray
            "#000000"
    };
}
