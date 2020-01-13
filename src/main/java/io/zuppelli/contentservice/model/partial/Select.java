package io.zuppelli.contentservice.model.partial;

import java.util.HashSet;
import java.util.Set;

public class Select extends Element {
    private Boolean checkBox = Boolean.FALSE;

    private Boolean radioButton = Boolean.FALSE;

    private Boolean multivalued = Boolean.FALSE;

    private Set<Option> options = new HashSet<>();

    public Select() {
        setType("select");
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(Boolean checkBox) {
        this.checkBox = checkBox;
    }

    public Boolean getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(Boolean radioButton) {
        this.radioButton = radioButton;
    }

    public Boolean getMultivalued() {
        return multivalued;
    }

    public void setMultivalued(Boolean multivalued) {
        this.multivalued = multivalued;
    }
}
