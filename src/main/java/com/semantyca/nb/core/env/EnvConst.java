package com.semantyca.nb.core.env;

import com.semantyca.nb.localization.constants.LanguageCode;

import java.util.Arrays;

public class EnvConst {

    public static final String SERVER_VERSION = "4.0.0";
    public static final String SERVER_NAME = "nb";
    public static final String FRAMEWORK_LOGO = "nextbase_logo_small.png";
    public static final String MAIN_PACKAGE = "com.semantyca";
    public static final String[] DEFAULT_LANGS = {"ENG", "RUS", "KAZ"};
    public static final String FSID_FIELD_NAME = "fsid";

    public static int DEFAULT_PAGE_SIZE = 20;
    public static String DEFAULT_DATE_FORMAT;
    public static String DEFAULT_DATETIME_FORMAT;
    public static String DEFAULT_TIME_FORMAT;
    public static String APP_ID;
    public static String DEFAULT_LANG = DEFAULT_LANGS[0];

    public static LanguageCode getDefaultLang() {
        return LanguageCode.valueOf(EnvConst.DEFAULT_LANG);
    }

    public static final LanguageCode[] getDefaultLangs(){
        return Arrays.stream(DEFAULT_LANGS).map((v)-> LanguageCode.valueOf(v)).toArray(LanguageCode[]::new);
    }
}