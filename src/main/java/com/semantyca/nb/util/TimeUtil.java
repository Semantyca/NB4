package com.semantyca.nb.util;

import com.semantyca.nb.core.env.EnvConst;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


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

    public static LocalDateTime stringToDateTime(String text) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            return LocalDateTime.parse(text, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    //needs to tune
    public static LocalDate getRndDate() {
        int year = NumberUtil.getRandomNumber(1900, 2017);
        int month = NumberUtil.getRandomNumber(1, 12);
        return LocalDate.of(year, month, NumberUtil.getRandomNumber(1, 30));

    }

    public static LocalDateTime getRndDateTime() {
        int year = NumberUtil.getRandomNumber(1900, 2017);
        int month = NumberUtil.getRandomNumber(1, 12);
        int hour = NumberUtil.getRandomNumber(0, 24);
        int min = NumberUtil.getRandomNumber(0, 60);
        return LocalDateTime.of(year, month, NumberUtil.getRandomNumber(1, 30), hour, min);

    }

    public static Date getRndDateBetween(Date from, Date to) {
        long random = ThreadLocalRandom.current().nextLong(from.getTime(), to.getTime());
        return new Date(random);
    }

}
