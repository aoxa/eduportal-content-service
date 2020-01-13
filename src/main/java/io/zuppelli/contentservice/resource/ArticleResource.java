package io.zuppelli.contentservice.resource;

import io.zuppelli.contentservice.exception.EntityNotFoundException;
import io.zuppelli.contentservice.model.Article;
import io.zuppelli.contentservice.model.Comment;
import io.zuppelli.contentservice.repository.NodeRepository;
import io.zuppelli.contentservice.resource.dto.ArticleDTO;
import io.zuppelli.contentservice.resource.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleResource {
    @Autowired
    private NodeRepository nodeRepository;

    @PostMapping()
    public Article add(@RequestBody @Valid ArticleDTO dto) {
        Article article = Article.builder().add("title", dto.getTitle())
                .add("body", dto.getBody()).add("author", dto.getUser())
                .build();
        return nodeRepository.save(article);
    }

    @PostMapping("/{id}/comment")
    public Article comment(@RequestBody @Valid CommentDTO dto, @PathVariable UUID id) {
        Article article = nodeRepository.findById(id).filter(n->n instanceof Article)
                .map(n->(Article)n).orElseThrow(EntityNotFoundException::new);
        Comment comment = new Comment();
        comment.setUser(dto.getUser());
        comment.setBody(dto.getBody());

        article.getChildren().add(comment);

        return nodeRepository.save(article);
    }
}
