package com.semantyca.nb.logger;

public class JavaConsoleLogger implements ILogger {

    @Override
    public void error(String logtext) {
        System.err.println(logtext);
    }

    @Override
    public void exception(Exception exception) {
        System.err.println(exception.toString());
        exception.printStackTrace();
    }

    @Override
    public void info(String logtext) {
        System.out.println(logtext);

    }

    @Override
    public void debug(String logtext) {
        System.out.println(logtext);

    }

    public void debugLogEntry(String agent, String logtext) {
        System.out.println(logtext);

    }

    @Override
    public void warning(String logtext) {
        System.out.println(logtext);

    }

    public void warningLogEntry(String agent, String logtext) {
        System.out.println(logtext);

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
