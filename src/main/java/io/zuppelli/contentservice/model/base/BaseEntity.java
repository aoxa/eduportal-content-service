package io.zuppelli.contentservice.model.base;

import io.zuppelli.contentservice.annotation.GenerateUUID;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public abstract class BaseEntity {
    @Id
    private UUID id;

    private Date creationDate;

    private Date modificationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}
