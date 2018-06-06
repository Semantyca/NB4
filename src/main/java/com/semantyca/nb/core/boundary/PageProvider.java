package com.semantyca.nb.core.boundary;

import com.semantyca.administrator.boundary.page.SignIn;

public class PageProvider {
    
    public Object getPage(String id){
        return new SignIn();
    }
    
}
