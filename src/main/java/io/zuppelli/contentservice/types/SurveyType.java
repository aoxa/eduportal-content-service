package io.zuppelli.contentservice.types;

import io.zuppelli.contentservice.model.Survey;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SurveyType extends NodeType {

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        super.init();
    }

    public String getEditRoleName() {
        return "edit_" + getType();
    }

    public String getAnswerRoleName() {
        return "answer_" + getType();
    }

    @Override
    public Class<Survey> nodeClass() {
        return Survey.class;
    }

    @Override
    public String getType() {
        return Survey.TYPE;
    }

    @Override
    public String getName() {
        return messageSource.getMessage(String.format("node.type.%s.name", Survey.TYPE), null, null);
    }

}
