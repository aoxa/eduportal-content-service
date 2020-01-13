package io.zuppelli.contentservice.model;

import io.zuppelli.contentservice.model.partial.Element;

import java.util.HashSet;
import java.util.Set;

public class SurveyReply extends NodeReply<Survey> {

    private Float score;

    private Set<Element> elements = new HashSet<>();

    public Set<Element> getElements() {
        return elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
