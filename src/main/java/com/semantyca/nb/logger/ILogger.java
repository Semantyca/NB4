package com.semantyca.nb.logger;

public interface ILogger {
    void info(String logtext);

    void error(String logtext);

    void warning(String logtext);

    void debug(String logtext);

    void exception(Exception e);

    void fatal(String logtext);

    void info(String process, String logtext);

    void error(String process, String logtext);

    void warning(String process, String logtext);

    void debug(String process, String logtext);

    void exception(String process, Exception e);

    void fatal(String process, String logtext);

}
