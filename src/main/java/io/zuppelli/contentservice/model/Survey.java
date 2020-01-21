package io.zuppelli.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zuppelli.contentservice.annotation.GenerateUUID;
import io.zuppelli.contentservice.model.partial.Element;
import io.zuppelli.contentservice.service.Builder;

import java.util.*;

@GenerateUUID
public class Survey extends Node {
    public final static String TYPE = "survey";

    public Survey() {
        setType(TYPE);
    }

    private List<Element> elements = new ArrayList<>();

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public static Builder<Survey> getBuilder() {
        return new SurveyBuilder();
    }

    private static class SurveyBuilder extends Builder<Survey> {

        public SurveyBuilder() {
            super(Survey.class);
        }

        @Override
        protected void prebuild() {

        }
    }
}
