package com.semantyca.nb.core.service;

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
            pages = ReflectionUtil.getPageClasses(EnvConst.MAIN_PACKAGE + ".nb.core.service.page");
            pages.putAll(ReflectionUtil.getPageClasses(EnvConst.MAIN_PACKAGE + ".nb.modules.administrator.service.page"));
            pages.putAll(ReflectionUtil.getPageClasses(EnvConst.MAIN_PACKAGE + ".officeframe.service.page"));
        }
        return pages.get(id);
    }


}

