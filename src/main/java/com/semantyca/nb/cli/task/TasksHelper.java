package com.semantyca.nb.cli.task;


import com.semantyca.nb.cli.Command;
import com.semantyca.nb.cli.Console;
import com.semantyca.nb.cli.Trigger;
import com.semantyca.nb.modules.administrator.dao.ModuleDAO;
import com.semantyca.nb.modules.administrator.model.Module;
import com.semantyca.nb.util.ReflectionUtil;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Kayra created 17-10-2016
 */

@EJB(name="task_helper")
public class TasksHelper {
    private static String TASKS_PACKAGE = ".task";
    private TreeMap<String, ServerTaskClass> tasks;

    @Inject
    private ModuleDAO dao;

    public TreeMap<String, ServerTaskClass> getAllTasks(boolean showConsoleOutput) {
        tasks = new TreeMap<String, ServerTaskClass>();

        if (showConsoleOutput) {
            System.out.printf(Console.advancedFormat, "Application", "Command", "Trigger");
            System.out.printf(Console.advancedFormat, "--------------", "-----", "-------");
        }


            List<Module> list = dao.findViewPage(0,0).getResult();

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

    public List<ServerTaskClass> getAllTriggeredTasks(String appName, Trigger trigger) {
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

    public ServerTaskClass getTaskClass(String command) {
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
