package io.zuppelli.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zuppelli.contentservice.model.base.BaseEntity;

import java.util.UUID;

public class NodeReply<T extends Node> extends BaseEntity {
    private UUID user;

    public UUID getUser() {
        return user;
    }

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
}
