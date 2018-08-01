package com.semantyca.nb.cli;


import com.semantyca.nb.modules.administrator.dao.ModuleDAO;
import com.semantyca.nb.modules.administrator.model.Module;
import com.semantyca.nb.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Kayra created 17-10-2016
 */

public class TasksHelper {
    private static String TASKS_PACKAGE = ".task";
    private static TreeMap<String, ServerTaskClass> tasks;

    public static TreeMap<String, ServerTaskClass> getAllTasks(boolean showConsoleOutput) {
        tasks = new TreeMap<String, ServerTaskClass>();

        if (showConsoleOutput) {
            System.out.printf(Console.advancedFormat, "Application", "Command", "Trigger");
            System.out.printf(Console.advancedFormat, "--------------", "-----", "-------");
        }

        ModuleDAO aDao = new ModuleDAO();
        List<Module> list = aDao.findViewPage(0,0).getResult();

        for (Module app : list) {
            if (app.isOn()) {
                String appName = app.getIdentifier();
                String appPackageName = appName.toLowerCase() + TASKS_PACKAGE;
                Collection<Class> appClasses = ReflectionUtil.getTaskClasses(appPackageName).values();
                for (Class<IServerScript> taskClass : appClasses) {
                    ServerTaskClass sc = new ServerTaskClass(appName, (taskClass));
                    tasks.put(sc.getCommand(), sc);
                    if (showConsoleOutput) {
                        outToConsole(appName, sc.getInitializerClass());
                    }
                }
            }
        }
        return tasks;
    }

    public static List<ServerTaskClass> getAllTriggeredTasks(String appName, Trigger trigger) {
        List<ServerTaskClass> triggerdTasks = new ArrayList<ServerTaskClass>();
        if (tasks == null) {
            getAllTasks(false);
        }
        for (ServerTaskClass t : tasks.values()) {
            if (t.getTrigger() == trigger && t.getAppName().equals(appName)) {
                triggerdTasks.add(t);
            }
        }
        return triggerdTasks;
    }

    public static ServerTaskClass getTaskClass(String command) {
        if (tasks == null) {
            getAllTasks(false);
        }
        return tasks.get(command);

    }

    private static void outToConsole(String appName, Class<IServerScript> clazz) {
        Command command = clazz.getAnnotation(Command.class);
        if (command != null) {
            System.out.printf(Console.advancedFormat, appName + ": ", command.name(), command.trigger());
        } else {
            System.out.printf(Console.advancedFormat, appName + ": ", clazz.getName(), "");
        }
    }
}
