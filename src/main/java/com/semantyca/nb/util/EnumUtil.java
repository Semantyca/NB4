package com.semantyca.nb.util;

import java.util.List;
import java.util.Random;

public class EnumUtil {

    public static <T> T getRndElement(T enumData[]) {
        Random rnd = new Random();
        return enumData[rnd.nextInt(enumData.length)];
    }

    public static <T> T getRndElement(List<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
