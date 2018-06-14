package com.semantyca.nb.modules.administrator.dao;

import com.semantyca.nb.core.dataengine.jpa.dao.SimpleDAO;
import com.semantyca.nb.modules.administrator.model.User;

import javax.ejb.Stateless;

@Stateless
public class UserDAO extends SimpleDAO<User,Long> {

}
