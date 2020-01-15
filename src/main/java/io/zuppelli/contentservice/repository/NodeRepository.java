package io.zuppelli.contentservice.repository;

import io.zuppelli.contentservice.model.Node;
import io.zuppelli.contentservice.model.NodeReply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NodeRepository extends MongoRepository<Node<? extends NodeReply>, UUID> {
    Node<?> findByTitle(String Title);

    @Query("{'children.user': ?0 }")
    List<Node> findAllByChildrenAuthor(UUID user);

    List<Node<?>> findAllByType(String type);

}
