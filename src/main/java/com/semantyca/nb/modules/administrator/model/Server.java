package com.semantyca.nb.modules.administrator.model;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.env.Environment;
import com.semantyca.nb.util.TimeUtil;

import java.io.File;

public class Server{

    public String id = "NB server";
    public String url = "/Administrator/server";
    public String startTime = TimeUtil.dateTimeToStringSilently(Environment.startTime);
    public boolean editable;
    public String orgName = Environment.orgName;
    public String version = EnvConst.SERVER_VERSION;
    public String os = System.getProperty("os.arch");
    public String jvm = System.getProperty("java.version");
    public int coresCount = Runtime.getRuntime().availableProcessors();
    public String freeMemory = Long.toString(Runtime.getRuntime().freeMemory() / 1024 / 1024);
    public String maxMemory = Long.toString(Runtime.getRuntime().maxMemory() / 1024 / 1024);
    public String totalMemory = Long.toString(Runtime.getRuntime().totalMemory() / 1024 / 1024);

    public String path;
    public String totalSpace;
    public String freeSpace;
    public String usableSpace;

    public Server() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            path = root.getAbsolutePath();
            totalSpace = Long.toString(root.getTotalSpace() / 1024 / 1024 / 1024);
            freeSpace = Long.toString(root.getFreeSpace() / 1024 / 1024 / 1024);
            usableSpace = Long.toString(root.getUsableSpace() / 1024 / 1024 / 1024);
        }
    }
}
