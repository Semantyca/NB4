package com.semantyca.nb.util;

import com.semantyca.nb.core.page.Page;
import com.semantyca.nb.core.page.XMLPage;
import com.semantyca.nb.logger.Lg;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {

    public static<T> Class<T> getClass(Class<T> pack) {
        return pack;

    }

    public static Map<String, XMLPage> getPageClasses(String pack) {

        Map<String, XMLPage> classes = new HashMap();
        URL upackage = ReflectionUtil.class.getClassLoader().getResource(pack.replace(".", File.separator));

        if (upackage != null) {
            BufferedReader dis = null;
            try {
                dis = new BufferedReader(new InputStreamReader((InputStream) upackage.getContent()));
                String line = null;
                while ((line = dis.readLine()) != null) {
                    if (line.endsWith(".class")) {
                        Class<?> clazz = Class.forName(pack + "." + line.substring(0, line.lastIndexOf('.')));
                        Page pageAnnotation = clazz.getAnnotation(Page.class);
                        if (pageAnnotation != null) {
                            String pageId = pageAnnotation.value();
                            if ("".equals(pageId)) {
                                pageId = clazz.getSimpleName().toLowerCase();
                            }
                            String pageXslt = pageAnnotation.xslt();
                            if ("".equals(pageXslt)) {
                                pageXslt = pageId + ".xsl";
                            }
                            classes.put(pageId, new XMLPage(pageId, pageXslt, clazz.getCanonicalName()));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (dis != null) {
                    try {
                        dis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            Lg.error("incorrect path " + pack);
        }
        return classes;
    }

}
