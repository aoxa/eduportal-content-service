package io.zuppelli.contentservice.repository;

import io.zuppelli.contentservice.model.NodeReply;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface NodeReplyRepository extends MongoRepository<NodeReply, UUID> {
    List<NodeReply> findAllByParent(UUID parent);
}
