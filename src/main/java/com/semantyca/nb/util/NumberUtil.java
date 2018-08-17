package com.semantyca.nb.util;

import java.util.Random;

public class NumberUtil {

    public static int getRandomNumber(int low, int high) {
        if (low == high) {
            return low;
        } else {
            Random r = new Random();
            int result = r.nextInt(high - low) + low;
            return result;
        }
    }


}
