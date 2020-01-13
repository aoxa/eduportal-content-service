package io.zuppelli.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.zuppelli.contentservice.model.base.BaseEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Node<T extends NodeReply> extends BaseEntity {
    private String type;

    private Course course;

    private String title;

    private String body;

    private String description;

    private Date limitDate;

    private UUID author;

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }

    @JsonIgnore
    private Set<T> children = new HashSet<>();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Set<T> getChildren() {
        return children;
    }

    public void setChildren(Set<T> children) {
        this.children = children;
    }
}
