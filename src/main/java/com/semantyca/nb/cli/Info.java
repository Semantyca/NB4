package com.semantyca.nb.cli;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.env.Environment;
import com.semantyca.nb.logger.ILogger;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

//import org.eclipse.persistence.Version;

public class Info {

    @SuppressWarnings("static-access")
    public static void showServerInfo() {
        System.out.printf(Console.format, "server version", EnvConst.SERVER_VERSION);
        System.out.printf(Console.format, "os",
                System.getProperty("os.version") + "(" + System.getProperty("os.arch") + ")");
        System.out.printf(Console.format, "jvm", System.getProperty("java.version"));
        LocalDateTime now = LocalDateTime.now();
        System.out.printf(Console.format, "server directory", new File("").getAbsolutePath());
        //   System.out.printf(Console.format, "JPA", Version.getVersionString());
        System.out.printf(Console.format, "default language", EnvConst.DEFAULT_LANG);


        if (Environment.translatorEnable) {
            System.out.printf(Console.format, "translator", "ON" + " (" + EnvConst.DEFAULT_TRANSLATOR_ENGINE + ")");
        } else {
            System.out.printf(Console.format, "translator", "OFF");
        }
        if (Environment.mapServiceEnable) {
            System.out.printf(Console.format, "map service", "ON" + " (" + EnvConst.DEFAULT_MAP_SERVICE_ENGINE + ")");
        } else {
            System.out.printf(Console.format, "map service", "OFF");
        }

        if (Environment.weatherServiceEnable) {
            System.out.printf(Console.format, "weather service", "ON" + " (" + EnvConst.DEFAULT_WEATHER_SERVICE_ENGINE + ", locality=" + Environment.weatherServiceLocality + ")");
        } else {
            System.out.printf(Console.format, "weather service", "OFF");
        }

        File file = new File(File.separator);
        long totalSpace = file.getTotalSpace();
        long freeSpace = file.getFreeSpace();
        System.out.printf(Console.format, "total disk size", totalSpace / 1024 / 1024 / 1024 + " gb");
        System.out.printf(Console.format, "space free", freeSpace / 1024 / 1024 / 1024 + " gb");
        if (Environment.mailEnable) {
            System.out.printf(Console.format, "mail agent", "ON");
        } else {
            System.out.printf(Console.format, "mail agent", "OFF");
        }
        System.out.printf(Console.format, "smtp port", Environment.smtpPort);
        System.out.printf(Console.format, "smtp auth", Environment.smtpAuth);
        System.out.printf(Console.format, "smtp server", Environment.smtpHost);
        System.out.printf(Console.format, "smtp user", Environment.smtpUser);
        System.out.printf(Console.format, "smtp user name", Environment.smtpUserName);

    }


    public static String showDatabaseSpeed(double index) {
        String addInfo = "";
        if (index < 0.7) {
            addInfo = " (very good)";
        } else if (index > 0.7 && index < 1.5) {
            addInfo = " (good)";
        } else if (index > 1.5 && index < 2.5) {
            addInfo = " (satisfactorily)";
        } else if (index > 2.5 && index < 3.5) {
            addInfo = " (slow)";
        } else if (index > 3.5 && index < 10) {
            addInfo = " (very slow)";
        } else if (index > 10) {
            addInfo = " (extremly slow)";
        }

        return index + addInfo;
    }





    private static void showVarInfo(Field field) {
        Class<?> t = field.getType();
        try {
            if (t == int.class) {
                System.out.printf(Console.format, field.getName(), field.getInt(null));
            } else {
                String name = field.getName();
                if (name.contains("PWD") || name.contains("PASSWORD")) {
                    System.out.printf(Console.format, field.getName(), "*****");
                } else {
                    System.out.printf(Console.format, field.getName(), field.get(null));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {

        }
    }


    public static void showJVMOptions(ILogger logger) {
        logger.info("available processors (cores): " + Runtime.getRuntime().availableProcessors());
        logger.info("free memory (mb): " + Runtime.getRuntime().freeMemory() / 1024 / 1024);
        long maxMemory = Runtime.getRuntime().maxMemory();
        logger.info("maximum memory (mb): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory / 1024 / 1024));
        logger.info("total memory (mb): " + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        File[] roots = File.listRoots();
        for (File root : roots) {
            logger.info("file system root: " + root.getAbsolutePath());
            logger.info("total space (mb): " + root.getTotalSpace() / 1024 / 1024 / 1024);
            logger.info("free space (mb): " + root.getFreeSpace() / 1024 / 1024 / 1024);
            logger.info("usable space (mb): " + root.getUsableSpace() / 1024 / 1024 / 1024);
        }

    }

}
