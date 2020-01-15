package io.zuppelli.contentservice.resource;

import io.zuppelli.contentservice.model.Node;
import io.zuppelli.contentservice.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeResource {
    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping
    public List<Node<?>> list() {
        return nodeRepository.findAll();
    }
}