package com.semantyca.nb.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalLogger implements ILogger {

    Logger logger = Logger.getLogger("main");

    @Override
    public void error(String logtext) {
        logger.log(Level.SEVERE, logtext);
    }

    @Override
    public void exception(Exception exception) {
        //System.err.println(exception.toString());
        logger.log(Level.SEVERE, exception.getMessage(), exception);
    }

    @Override
    public void info(String logtext) {
        logger.log(Level.INFO, logtext);

    }

    @Override
    public void debug(String logtext) {
        logger.log(Level.FINE, logtext);

    }

    @Override
    public void warning(String logtext) {
        logger.log(Level.WARNING, logtext);

    }


    @Override
    public void fatal(String logtext) {
        System.err.println(logtext);

    }

    @Override
    public void info(String process, String logtext) {
        System.out.println(process + "-" + logtext);

    }

    @Override
    public void error(String process, String logtext) {
        System.out.println(process + "-" + logtext);

    }

    @Override
    public void warning(String process, String logtext) {
        System.out.println(process + "-" + logtext);

    }

    @Override
    public void debug(String process, String logtext) {
        System.out.println(process + "-" + logtext);

    }

    @Override
    public void exception(String process, Exception e) {
        System.err.println(process + "-" + e.toString());
        e.printStackTrace();

    }

    @Override
    public void fatal(String process, String logtext) {
        System.err.println(process + "-" + logtext);

    }

}
