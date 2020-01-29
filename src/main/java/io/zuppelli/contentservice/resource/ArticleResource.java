package io.zuppelli.contentservice.resource;

import io.zuppelli.contentservice.exception.EntityNotFoundException;
import io.zuppelli.contentservice.model.Article;
import io.zuppelli.contentservice.model.Comment;
import io.zuppelli.contentservice.model.Node;
import io.zuppelli.contentservice.model.NodeReply;
import io.zuppelli.contentservice.repository.NodeReplyRepository;
import io.zuppelli.contentservice.repository.NodeRepository;
import io.zuppelli.contentservice.resource.dto.ArticleDTO;
import io.zuppelli.contentservice.resource.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticleResource {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private NodeReplyRepository nodeReplyRepository;

    @GetMapping
    public List<Node<?>> list() {
        return nodeRepository.findAllByType(Article.TYPE);
    }

    @PostMapping()
    public Article add(@RequestBody @Valid ArticleDTO dto) {
        Article article = Article.builder().add("title", dto.getTitle())
                .add("body", dto.getBody()).add("author", dto.getUser())
                .build();
        return nodeRepository.save(article);
    }

    @GetMapping("/{id}")
    public Node get(@PathVariable UUID id) {
        return nodeRepository.findById(id).filter(n->n instanceof Article).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/{id}")
    public Article edit(@PathVariable UUID id, @RequestBody @Valid ArticleDTO dto) {
        Article article = findArticle(id);
        article.setBody(dto.getBody());
        article.setTitle(dto.getTitle());

        if(!dto.getUser().equals(dto.getUser())) article.getContributors();

        return nodeRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        nodeRepository.delete(findArticle(id));
    }

    @PostMapping("/{id}/comment")
    public NodeReply<Article> comment(@RequestBody @Valid CommentDTO dto, @PathVariable UUID id) {
        Comment comment = new Comment();
        comment.setUser(dto.getUser());
        comment.setBody(dto.getBody());
        comment.setParent(id);

        return nodeReplyRepository.save(comment);
    }

    @GetMapping("/{id}/comment")
    public List<NodeReply> comments(@PathVariable UUID id) {
        return nodeReplyRepository.findAllByParent(id);
    }

    private Article findArticle(@PathVariable UUID id) {
        return nodeRepository.findById(id).filter(n->n instanceof Article)
                    .map(n->(Article)n).orElseThrow(EntityNotFoundException::new);
    }
}
