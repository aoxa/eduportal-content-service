package io.zuppelli.contentservice.resource.dto;

import java.util.List;

public class ElementDTO {
    private String type;
    private String title;
    private String tip;
    private List<ValueDTO> values;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ValueDTO> getValues() {
        return values;
    }

    public void setValues(List<ValueDTO> values) {
        this.values = values;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
