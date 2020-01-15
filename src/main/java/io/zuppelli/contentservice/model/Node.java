package io.zuppelli.contentservice.model;

import io.zuppelli.contentservice.annotation.UpdateDates;
import io.zuppelli.contentservice.model.base.BaseEntity;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Collections;

public class Node<T extends NodeReply> extends BaseEntity {
    private String type;

    private Course course;

    private String title;

    private String body;

    private String description;

    private Date limitDate;

    private UUID author;

    private List<T> children = new ArrayList<>();

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

    public List<T> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @UpdateDates
    public void addChild(T child) {
        this.children.add(child);
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }
}
