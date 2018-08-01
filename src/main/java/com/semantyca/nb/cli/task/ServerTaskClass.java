package com.semantyca.nb.cli.task;

import com.semantyca.nb.cli.Command;
import com.semantyca.nb.cli.Trigger;

import java.time.LocalDateTime;

public class ServerTaskClass {
    private String appName;
    private Class<IServerScript> initializerClass;
    private String command;
    private Trigger trigger;
    private LocalDateTime lastRun;
    private LocalDateTime nextRun;
    private String log;

    public ServerTaskClass() {
    }

    public ServerTaskClass(String appName, Class<IServerScript> initializerClass) {
        this.appName = appName;
        this.initializerClass = initializerClass;
        Command commandAnotation = initializerClass.getAnnotation(Command.class);
        command = commandAnotation.name();
        trigger = commandAnotation.trigger();
    }

    public String getId() {
        return command;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Class<IServerScript> getInitializerClass() {
        return initializerClass;
    }

    public void setInitializerClass(Class<IServerScript> initializerClass) {
        this.initializerClass = initializerClass;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public LocalDateTime getLastRun() {
        return lastRun;
    }

    public void setLastRun(LocalDateTime lastRun) {
        this.lastRun = lastRun;
    }

    public LocalDateTime getNextRun() {
        return nextRun;
    }

    public void setNextRun(LocalDateTime nextRun) {
        this.nextRun = nextRun;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        Command command = initializerClass.getAnnotation(Command.class);
        return initializerClass.getName() + " command=" + command.name() + ", trigger=" + command.trigger();
    }
}
