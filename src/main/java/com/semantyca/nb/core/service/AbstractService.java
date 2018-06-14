package com.semantyca.nb.core.service;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.IDAO;
import com.semantyca.nb.core.rest.RestProvider;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.ui.view.ViewOption;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.UUID;

public abstract class AbstractService<T extends IAppEntity> extends RestProvider {
    protected Class<T> entityClass;
    protected IDAO<T, UUID> dao;


    @PostConstruct
    public void init() {
//        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      //  dao = DAOFactory.get(getUserSession(), entityClass);
    }

    public ViewOption getDefaultViewOption() {
        return null;
    }



    protected T getEntity(UUID id){
        return dao.findById(id);
    }

    protected T getEntity(T dto){
        T entity;
        if (dto.isNew()) {
            try {
                Class<T> clazz = (Class<T>) dto.getClass();
                entity = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                Lg.exception(e);
                return null;
            }
        } else {
            entity = dao.findById(dto.getId());
        }
        return entity;
    }


    private void writeToFile(InputStream uploadedInputStream, File uploadedFileLocation) {
        try {
            OutputStream out = new FileOutputStream(uploadedFileLocation);
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(uploadedFileLocation);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            Lg.exception(e);
        }
    }


}
