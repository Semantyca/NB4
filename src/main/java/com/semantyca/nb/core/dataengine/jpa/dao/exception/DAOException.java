package com.semantyca.nb.core.dataengine.jpa.dao.exception;

import com.semantyca.nb.core.env.Environment;
import com.semantyca.nb.localization.constants.LanguageCode;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.postgresql.util.PSQLException;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.io.IOException;

public class DAOException extends Exception {
    private static final long serialVersionUID = 1L;
    private DAOExceptionType id = DAOExceptionType.INTERNAL_PERSISTENCE_EXCEPTION;
    private String addInfo;

    public DAOException(Exception exception) {
        super(exception);
        addInfo = exception.getMessage();

    }

    public DAOException(PersistenceException exception) {
        super(exception);
        if (exception.getCause() instanceof DatabaseException) {
            DatabaseException de = (DatabaseException) exception.getCause();
            PSQLException psqlException = (PSQLException) de.getCause();
            String code = psqlException.getSQLState();
            if (code.equals("23505")) {
                id = DAOExceptionType.UNIQUE_VIOLATION;
                addInfo = psqlException.getMessage();
            } else if (code.equals("23502")) {
                id = DAOExceptionType.NOT_NULL_VIOLATION;
                addInfo = psqlException.getMessage();
            } else if (code.equals("23503")) {
                id = DAOExceptionType.VIOLATES_FOREIGN_KEY;
                addInfo = psqlException.getMessage();
            } else if (code.equals("22001")) {
                id = DAOExceptionType.VALUE_TOO_LONG;
                addInfo = psqlException.getMessage();
            } else if (code.equals("42883")) {
                id = DAOExceptionType.ID_IS_NULL;
                addInfo = psqlException.getMessage();
            } else if (code.equals("22023")) {
                if (psqlException.getCause() instanceof IOException) {
                    addInfo = psqlException.getCause().getMessage();
                    // TODO it is not common case but the most frequent
                    id = DAOExceptionType.FILE_TOO_LARGE;
                } else {
                    addInfo = psqlException.getMessage();
                }
            } else {
                addInfo = psqlException.getMessage();
            }
        } else if (exception.getCause() instanceof RollbackException) {
            addInfo = exception.getMessage();
        } else if (exception instanceof NonUniqueResultException || exception.getCause() instanceof NonUniqueResultException) {
            id = DAOExceptionType.NOT_UNIQUE_RESULT;
            addInfo = exception.getMessage();
        } else {
            addInfo = exception.getMessage();
        }
    }

    public DAOException(DAOExceptionType type, String text) {
        super(type.name() + ", addInfo=" + text);
        id = type;
        addInfo = text;
    }

    public DAOException(DAOExceptionType type, String text, Exception e) {
        super(type.name() + ", addInfo=" + text, e);
        id = type;
        addInfo = text;
    }

    public DAOExceptionType getType() {
        return id;
    }

    public void setAddInfo(LanguageCode lang, String ai) {
        addInfo = Environment.vocabulary.getWord(ai, lang);
        ;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String ai) {
        addInfo = ai;
    }

    public String getLocMessage(LanguageCode lang) {
        return Environment.vocabulary.getWord(id.name(), lang);
    }

}
