package io.zuppelli.contentservice.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Setting<T extends Serializable> {

    @Id
    private String name;

    private Serializable value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return (T) value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }

}
