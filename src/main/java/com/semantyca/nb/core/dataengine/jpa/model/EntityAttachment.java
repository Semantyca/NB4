package com.semantyca.nb.core.dataengine.jpa.model;

import javax.persistence.*;
import java.util.List;


@Embeddable
//@Customizer(NamingCustomizer.class)
public class EntityAttachment {

    @Column(length = 64)
    private String fileName;
    @Column(length = 32)
    private String extension;
    private long size;
    private boolean hasThumbnail = false;
    private String comment;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String realFileName) {
        // file extension
        int lastDotIndex = realFileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            extension = realFileName.substring(lastDotIndex + 1).toLowerCase();
        } else {
            extension = "";
        }
        //
        this.fileName = realFileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
        if (file != null) {
            size = file.length;
        } else {
            size = 0;
        }
    }

    public boolean isHasThumbnail() {
        return hasThumbnail;
    }

    public void setHasThumbnail(boolean hasThumbnail) {
        this.hasThumbnail = hasThumbnail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<EntityAttachment> getAttachments() {
        return null;
    }

}
