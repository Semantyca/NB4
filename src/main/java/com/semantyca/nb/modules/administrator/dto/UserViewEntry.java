package com.semantyca.nb.modules.administrator.dto;

import com.semantyca.nb.core.dataengine.jpa.dto.IDTO;
import com.semantyca.nb.modules.administrator.init.ModuleConst;

public class UserViewEntry implements IDTO {

    public Long id;
    public String login;
    public String email;

    public UserViewEntry(Long id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public String getURL() {
        return ModuleConst.BASE_URL + "users/" + id;
    }

}
