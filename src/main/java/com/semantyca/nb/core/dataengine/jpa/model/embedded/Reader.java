package com.semantyca.nb.core.dataengine.jpa.model.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Reader {

    @Column(name = "was_read")
    private boolean wasRead;

    @Column(name = "reading_time")
    private Date readingTime;

    @Column(nullable = false, name = "user_id")
    private Long user;

    public Long getUser() {
        return user;
    }

    public void setUser(Long reader) {
        this.user = reader;
    }

    public boolean isWasRead() {
        return wasRead;
    }


    public void setWasRead(boolean wasRead) {
        if (wasRead != this.wasRead) {
            if (wasRead) {
                readingTime = new Date();
                this.wasRead = true;
            } else {
                readingTime = null;
                this.wasRead = false;
            }
        }
    }

    public Date getReadingTime() {
        return readingTime;
    }
}