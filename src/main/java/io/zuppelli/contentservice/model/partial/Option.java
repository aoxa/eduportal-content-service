package io.zuppelli.contentservice.model.partial;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option extends Element {
    private String value;

    private Boolean selected;

    public Option() {
        setType("option");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected= selected;
    }
}
