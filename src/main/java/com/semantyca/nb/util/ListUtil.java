package com.semantyca.nb.util;

import java.util.List;
import java.util.Random;

public class ListUtil {

    public static Object getRndElement(List<?> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public static Object getRndElement(Object[] list) {
        Random random = new Random();
        int index = random.nextInt(list.length);
        return list[index];
    }
}
