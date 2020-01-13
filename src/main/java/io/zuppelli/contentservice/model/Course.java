package io.zuppelli.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Course {
    @Id
    private Long id;

    private String name;

    private String description;

    private UUID neededRole;

    @JsonIgnore
    private Set<UUID> authorities = new HashSet<>();

    @JsonIgnore
    private Set<UUID> enrolled = new HashSet<>();

    private Set<Node> nodes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UUID> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UUID> authorities) {
        this.authorities = authorities;
    }

    public Set<UUID> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Set<UUID> enrolled) {
        this.enrolled = enrolled;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public UUID getNeededRole() {
        return neededRole;
    }

    public void setNeededRole(UUID neededRole) {
        this.neededRole = neededRole;
    }

}
