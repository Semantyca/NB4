package com.semantyca.nb.core.rest.outgoing;

import com.semantyca.nb.core.dataengine.jpa.dao.exception.DAOException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorOutcome extends Outcome {
    public static final String ERROR_PAYLOAD = "error";

    public ErrorOutcome(DAOException e) {
        super();
        payload.put(ERROR_PAYLOAD, e);
    }


}
