package com.semantyca.nb.modules.administrator.task;

import com.semantyca.nb.cli.Command;
import com.semantyca.nb.cli.task.Do;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.modules.administrator.dao.LanguageDAO;
import com.semantyca.nb.modules.administrator.init.ModuleConst;
import com.semantyca.nb.modules.administrator.init.ServerConst;
import com.semantyca.nb.modules.administrator.model.Language;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

//run task adm_register_langs
@Command(name = ModuleConst.CODE + "_register_langs")
public class RegisterLangs extends Do {

    @Override
    public void doTask(Session ses) {
        System.out.println("register languages...");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        try {
            LanguageDAO dao = (LanguageDAO)container.getContext().lookup("LanguageDAO");
            for (String lc : EnvConst.DEFAULT_LANGS) {
                Language entity = ServerConst.getLanguage(LanguageCode.valueOf(lc));
                dao.add(entity);
                Lg.info(entity.getCode() + " language was added, activated=" + entity.isOn());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }


        System.out.println("done");
    }

}
