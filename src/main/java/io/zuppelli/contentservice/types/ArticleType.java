package io.zuppelli.contentservice.types;

import io.zuppelli.contentservice.model.Article;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ArticleType extends NodeType {

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
    public Class<Article> nodeClass() {
        return Article.class;
    }

    @Override
    public String getType() {
        return Article.TYPE;
    }

    @Override
    public String getName() {
        return messageSource.getMessage(String.format("node.type.%s.name", Article.TYPE), null, null);
    }
}
