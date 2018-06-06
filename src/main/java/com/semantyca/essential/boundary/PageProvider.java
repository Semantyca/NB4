package com.semantyca.essential.boundary;

import com.semantyca.essential.administrator.boundary.page.SignIn;

public class PageProvider {
    
    public Object getPage(String id){
        return new SignIn();
    }
    
}
