package io.zuppelli.contentservice.types;

import io.zuppelli.contentservice.model.Node;
import io.zuppelli.contentservice.service.RoleService;
import io.zuppelli.contentservice.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.UUID;

public abstract class NodeType {
    @Autowired
    protected MessageSource messageSource;

    @Autowired
    private NodeTypeService nodeTypeService;

    @Autowired
    private RoleService roleService;

    private UUID editRole;
    private UUID answerRole;

    public abstract Class<? extends Node> nodeClass();
    public abstract String getType();
    public abstract String getName();
    public abstract String getEditRoleName();
    public abstract String getAnswerRoleName();

    public final UUID getEditRole() {
        return editRole;
    }

    public final UUID getAnswerRole(){
        return answerRole;
    }


    protected void init() {
        nodeTypeService.register(this);

        editRole = roleService.findByName(getEditRoleName());
        if(null == editRole) {
            editRole = buildRole(getEditRoleName());
        }

        answerRole = roleService.findByName(getAnswerRoleName());
        if(null == answerRole) {
            answerRole = buildRole(getAnswerRoleName());
        }
    }

    protected final UUID buildRole(String roleName) {
        RoleDTO role = new RoleDTO();
        role.setName(roleName);
        role.setType("node");

        return roleService.save(role);
    }
}
