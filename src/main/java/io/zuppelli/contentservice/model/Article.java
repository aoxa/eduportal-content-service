package io.zuppelli.contentservice.model;

import io.zuppelli.contentservice.annotation.GenerateUUID;
import io.zuppelli.contentservice.annotation.UpdateDates;
import io.zuppelli.contentservice.service.Builder;

import java.util.List;
import java.util.UUID;

@GenerateUUID
@UpdateDates
public class Article extends Node<Comment> {
    public static final String TYPE = "article";

    private List<UUID> contributors;

    public Article() {
        this.setType(TYPE);
    }

    public static Builder builder(){
        return new Builder();
    }

    public List<UUID> getContributors() {
        return contributors;
    }

    public void setContributors(List<UUID> contributors) {
        this.contributors = contributors;
    }

    public static class Builder extends io.zuppelli.contentservice.service.Builder<Article> {

        public Builder() {
            super(Article.class);
        }

        @Override
        protected void prebuild() {

        }
    }
}
