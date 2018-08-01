package com.semantyca.nb.core.env;


import com.semantyca.nb.cli.Console;
import com.semantyca.nb.localization.Vocabulary;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import static javax.ejb.ConcurrencyManagementType.BEAN;


@Singleton
@Startup
@ConcurrencyManagement(BEAN)
public class Environment {

    public static LocalDateTime startTime;
    public static String orgName;
    public static String orgShortName;
    public static String color;
    public static String logo;
    public static String wallpaper;

    public static String tmpDir;
    public static String trash;

    public static Boolean mailEnable = false;
    public static String smtpPort = "25";
    public static boolean smtpAuth;
    public static String smtpHost;
    public static String smtpUser;
    public static String smtpPassword;
    public static String smtpUserName;

    public static Boolean slackEnable = false;
    public static String slackToken;

    public static Boolean translatorEnable = false;
    public static String translatorEngine = EnvConst.DEFAULT_TRANSLATOR_ENGINE;
    public static String yandexTranslatorApiKey;

    public static Boolean weatherServiceEnable = false;
    public static String weatherServiceEngine = EnvConst.DEFAULT_WEATHER_SERVICE_ENGINE;
    public static String weatherServiceApiKey;
    public static String weatherServiceLocality;

    public static Boolean mapServiceEnable = false;
    public static String mapEngine = EnvConst.DEFAULT_MAP_SERVICE_ENGINE;
    public static String mapsApiKey;

    public static boolean XMPPServerEnable;
    public static String XMPPServer;
    public static String XMPPLogin;
    public static String XMPPPwd;

    public static Vocabulary vocabulary;

    @PostConstruct
    public void init() {
        startTime = LocalDateTime.now();
        ServerProperties.loadProperties();
        initProcess();

        RunnableFuture<Boolean> f = new FutureTask<>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                loadTemplateSet();
                return true;
            }
        });
        new Thread(f).start();

    }

    private static void initProcess() {
        Thread thread = new Thread(new Console());
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    private static void loadTemplateSet () {

    }
}
