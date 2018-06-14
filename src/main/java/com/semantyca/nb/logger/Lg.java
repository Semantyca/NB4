package com.semantyca.nb.logger;

public class Lg {
    static ILogger logger = new GlobalLogger();

    public static void info(String logtext) {
        logger.info(logtext);
    }

    public static void info(String agent, String logtext) {
        logger.info(agent, logtext);
    }

    public static void error(String logtext) {
        logger.error(logtext);
    }

    public static void error(Exception e) {
        logger.error(e.toString());
    }

    public static void error(String process, String logtext) {
        logger.error(process, logtext);
    }

    public static void warning(String logtext) {
        logger.warning(logtext);
    }

    public static void debug(String logtext) {
        logger.debug(logtext);
    }

    public static void debug(String agent, String logtext) {
        logger.debug(agent, logtext);
    }

    public static void exception(Exception e) {
        logger.exception(e);
    }

    public static void fatal(String logtext) {
        logger.fatal(logtext);
    }





}
