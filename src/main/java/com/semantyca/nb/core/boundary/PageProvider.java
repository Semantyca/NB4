package com.semantyca.nb.core.boundary;

import com.semantyca.administrator.boundary.page.SignIn;

import javax.ejb.Stateless;

@Stateless
public class PageProvider {
   // private static List<Class> pages;

    public Object getPage(String id){
       /* if (pages == null){
            pages = ReflectionUtil.getAnnotatedClasses(EnvConst.MAIN_PACKAGE + ".administrator.boundary.page", Page.class);
            pages.addAll(ReflectionUtil.getAnnotatedClasses(EnvConst.MAIN_PACKAGE + ".officeframe.boundary.page", Page.class));
        }*/
        return new SignIn();
    }
    
}
