package io.zuppelli.contentservice.model;

public class Comment extends NodeReply<Article> {
    public Comment() {
        this.setType("comment");
    }

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
