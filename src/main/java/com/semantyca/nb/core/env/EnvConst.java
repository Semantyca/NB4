package com.semantyca.nb.core.env;

import com.semantyca.nb.localization.constants.LanguageCode;

import java.util.Arrays;

public class EnvConst {

    public static final String SERVER_VERSION = "4.0.0";
    public static final String SERVER_NAME = "nb4";
    public static final String FRAMEWORK_LOGO = "nextbase_logo_small.png";
    public static final String MAIN_PACKAGE = "com.semantyca";
    public static final String[] DEFAULT_LANGS = {"ENG", "RUS", "KAZ"};
    public static final String FSID_FIELD_NAME = "fsid";

    public static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    public static int DEFAULT_PAGE_SIZE = 25;
    public static String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    public static String DEFAULT_DATETIME_FORMAT = "dd.MM.yyyy kk:mm";
    public static String DEFAULT_TIME_FORMAT = "kk:mm";
    public static String APP_ID;
    public static String DEFAULT_LANG = DEFAULT_LANGS[0];

    public static String DEFAULT_TRANSLATOR_ENGINE = "yandex";
    public static String DEFAULT_MAP_SERVICE_ENGINE = "google";
    public static String DEFAULT_WEATHER_SERVICE_ENGINE = "openweather.org";


    public static LanguageCode getDefaultLang() {
        return LanguageCode.valueOf(EnvConst.DEFAULT_LANG);
    }

    public static final LanguageCode[] getDefaultLangs(){
        return Arrays.stream(DEFAULT_LANGS).map((v)-> LanguageCode.valueOf(v)).toArray(LanguageCode[]::new);
    }
}