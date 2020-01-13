package io.zuppelli.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zuppelli.contentservice.annotation.GenerateUUID;
import io.zuppelli.contentservice.model.partial.Element;

import java.util.*;

@GenerateUUID
public class Survey extends Node {
    public final static String TYPE = "survey";

    public Survey() {
        setType(TYPE);
    }

    @JsonIgnore
    private Set<Element> elements = new HashSet<>();

    public List<Element> getSortedElements() {
        List<Element> result = new ArrayList<>(this.elements);
        result.sort(Comparator.comparing(Element::getWeight));

        return result;
    }

    public Set<Element> getElements() {
        return elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }
}
