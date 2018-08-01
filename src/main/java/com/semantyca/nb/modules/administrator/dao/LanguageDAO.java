package com.semantyca.nb.modules.administrator.dao;

import com.semantyca.nb.core.dataengine.jpa.dao.DAO;
import com.semantyca.nb.modules.administrator.model.Language;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class LanguageDAO extends DAO<Language, UUID> {


}
