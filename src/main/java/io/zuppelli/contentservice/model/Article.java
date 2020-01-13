package io.zuppelli.contentservice.model;

import io.zuppelli.contentservice.annotation.GenerateUUID;
import io.zuppelli.contentservice.service.Builder;

@GenerateUUID
public class Article extends Node<Comment> {
    public static final String TYPE = "article";
    public Article() {
        this.setType(TYPE);
    }

    public static Builder builder(){
        return new Builder();
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
