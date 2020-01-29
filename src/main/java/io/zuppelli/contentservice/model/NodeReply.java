package io.zuppelli.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zuppelli.contentservice.annotation.GenerateUUID;
import io.zuppelli.contentservice.annotation.UpdateDates;
import io.zuppelli.contentservice.model.base.BaseEntity;

import java.util.UUID;


public class NodeReply<T extends Node> extends BaseEntity {
    private UUID user;

    public UUID getUser() {
        return user;
    }

    public UUID parent;

    private String type;

    public void setUser(UUID user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }
}
