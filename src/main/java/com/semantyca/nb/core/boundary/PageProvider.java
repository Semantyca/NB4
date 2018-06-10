package com.semantyca.nb.core.boundary;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.page.XMLPage;
import com.semantyca.nb.util.ReflectionUtil;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class PageProvider {
    private static Map<String, XMLPage> pages;

    public XMLPage getPage(String id) {

        if (pages == null) {
            pages = ReflectionUtil.getPageClasses(EnvConst.MAIN_PACKAGE + ".nb.core.boundary.page");
            pages.putAll(ReflectionUtil.getPageClasses(EnvConst.MAIN_PACKAGE + ".administrator.boundary.page"));
            pages.putAll(ReflectionUtil.getPageClasses(EnvConst.MAIN_PACKAGE + ".officeframe.boundary.page"));
        }
        return pages.get(id);
    }


}

