package com.semantyca.nb.cli;


import com.semantyca.nb.cli.task.ConsoleTaskRunner;
import com.semantyca.nb.cli.task.InfoMessageType;
import com.semantyca.nb.cli.task.ServerTaskOutcome;
import com.semantyca.nb.logger.JavaConsoleLogger;
import com.semantyca.nb.logger.Lg;

import java.util.Scanner;

public class Console implements Runnable {
    public static final String format = "%-40s%s%n";
    public static final String superShortFormat = "%-22s%s%n";
    public static final String shortFormat = "%-20s%s%n";
    public static final String advancedFormat = "%-20s%-25s%s%n";


    @Override
    public void run() {
        @SuppressWarnings("resource") final Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            try {
                String command = in.nextLine();
                cliHandler(command);
            } catch (Exception e) {
                Lg.exception(e);
            } finally {
                // in.close();
            }
        }
    }

    public void cliHandler(String command) {
        command = command.trim();
        if (command.startsWith("echo") || command.startsWith("log")) {
            String echo = getThirdParameter(command, "echo", "log");
            System.out.println(echo);
        } else {
            System.out.println("> " + command);
            if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("q")) {
                //Server.shutdown();
            } else if (command.equalsIgnoreCase("clean display") || command.equalsIgnoreCase("cls")) {
                System.out.print("\033\143");

            } else if (command.equalsIgnoreCase("info") || command.equalsIgnoreCase("i")) {
                Info.showServerInfo();
            } else if (command.equalsIgnoreCase("show jvm options") || command.equalsIgnoreCase("sjo")) {
                Info.showJVMOptions(new JavaConsoleLogger());
            } else if (command.equalsIgnoreCase("show tasks") || command.equalsIgnoreCase("st")) {
                TasksHelper.getAllTasks(true);
            } else if (command.contains("run task") || command.startsWith("rt")) {
                String taskCommand = getThirdParameter(command, "run task", "rt");
                if (taskCommand.trim().isEmpty()) {
                    System.err.println("error -task name is empty");
                } else {
                    ConsoleTaskRunner ct = new ConsoleTaskRunner();
                    ServerTaskClass clazz = TasksHelper.getTaskClass(taskCommand);
                    if (clazz != null) {
                        ServerTaskOutcome outcome = ct.processCode(clazz);
                        if (outcome.getType() != InfoMessageType.OK) {
                            System.err.println(outcome.getException());
                        }
                    } else {
                        System.err.println("\"" + taskCommand + "\" task has not been found");
                    }
                }

            } else if (command.contains("run batch") || command.startsWith("rubat")) {
              /*  String batch = getThirdParameter(command, "run batch", "rubat");
                if (batch.trim().equals("")) {
                    System.err.println("error -batch name is empty");
                } else {
                    try (BufferedReader br = new BufferedReader(
                            new FileReader(EnvConst.RESOURCES_DIR + File.separator + batch))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (!line.startsWith("#")) {
                                cliHandler(line);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("\"" + batch + "\" batch file not found");
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    System.out.println("the batch \"" + batch + "\"  has been done");
                }*/
            } else if (command.contains("show batch") || command.startsWith("shbat")) {
              /*  String batch = getThirdParameter(command, "show batch", "shbat");
                if (batch.trim().equals("")) {
                    System.err.println("error -batch name is empty");
                } else {
                    try (BufferedReader br = new BufferedReader(new FileReader(EnvConst.RESOURCES_DIR + File.separator + batch))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("\"" + batch + "\" batch file not found");
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    System.out.println("the batch \"" + batch + "\"  has been done");
                }             */
            } else if (command.equals("help") || command.equalsIgnoreCase("h")) {

            } else {
                if (!command.trim().equalsIgnoreCase("")) {
                    System.err.println("error -command \"" + command
                            + "\" is not recognized, try to type 'help' to connect a short guide about commands");
                }
            }
        }
    }

    private String getThirdParameter(String command, String name, String shortName) {
        int start = 0;
        if (command.contains(name)) {
            start = name.length();
        } else if (command.startsWith(shortName)) {
            start = shortName.length();
        }
        return command.substring(start).trim();
    }
}
