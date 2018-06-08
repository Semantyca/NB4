package com.semantyca.nb.util;

import java.io.*;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {


    public static List<Class> getAnnotatedClasses(String pack, Class<? extends Annotation> ann) {

        List<Class> classes = new ArrayList<Class>();
        URL upackage = ReflectionUtil.class.getClassLoader().getResource(pack.replace(".", File.separator));

        BufferedReader dis = null;
        try {
            dis = new BufferedReader(new InputStreamReader((InputStream) upackage.getContent()));
            String line = null;
            while ((line = dis.readLine()) != null) {
                if (line.endsWith(".class")) {
                    Class clazz = Class.forName(pack + "." + line.substring(0, line.lastIndexOf('.')));
                    if (clazz.isAnnotationPresent(ann)) {
                        classes.add(clazz);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

}
