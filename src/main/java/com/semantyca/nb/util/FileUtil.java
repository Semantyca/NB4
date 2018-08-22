package com.semantyca.nb.util;

import java.io.File;
import java.util.Random;

public class FileUtil {

    public static File getRndFile(String directory) throws Exception {
        int maxIt = 10, it = 0;
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            throw new Exception("there are no files or directory");
        }
        File file = null;
        do {
            Random rand = new Random();
            file = files[rand.nextInt(files.length)];
            it++;
        } while (file.isDirectory() && it < maxIt);
        return file;
    }
}
