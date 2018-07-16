package com.semantyca.nb.util;

import com.semantyca.nb.core.env.EnvConst;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(EnvConst.DEFAULT_DATETIME_FORMAT);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(EnvConst.DEFAULT_DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMAT_FOR_FILE_NAMING = DateTimeFormatter.ofPattern("dd-MM-yyyy_kk-mm");

    public static String dateTimeToStringSilently(LocalDateTime dateTime) {
        try {
            return DATE_TIME_FORMAT.format(dateTime);
        } catch (Exception e) {
            return "";
        }
    }

}
