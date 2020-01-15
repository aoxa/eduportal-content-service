package io.zuppelli.contentservice.resource;

import io.zuppelli.contentservice.exception.EntityNotFoundException;
import io.zuppelli.contentservice.model.*;
import io.zuppelli.contentservice.repository.NodeRepository;
import io.zuppelli.contentservice.resource.dto.ArticleDTO;
import io.zuppelli.contentservice.resource.dto.CommentDTO;
import io.zuppelli.contentservice.resource.dto.ReplyDTO;
import io.zuppelli.contentservice.resource.dto.SurveyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/surveys")
public class SurveyResource {
    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping
    public List<Node<?>> list() {
        return nodeRepository.findAllByType(Survey.TYPE);
    }

    @PostMapping()
    public Survey add(@RequestBody @Valid SurveyDTO dto) {
        Survey survey = new Survey();

        return nodeRepository.save(survey);
    }

    @GetMapping("/{id}")
    public Node get(@PathVariable UUID id) {
        return nodeRepository.findById(id).filter(n->n instanceof Survey).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/{id}")
    public Survey edit(@PathVariable UUID id, @RequestBody @Valid SurveyDTO dto) {
        Survey survey = findSurvey(id);
        survey.setBody(dto.getBody());
        survey.setTitle(dto.getTitle());

        return nodeRepository.save(survey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        nodeRepository.delete(findSurvey(id));
    }

    @PostMapping("/{id}/reply")
    public Survey comment(@RequestBody @Valid ReplyDTO dto, @PathVariable UUID id) {
        Survey survey = findSurvey(id);
        SurveyReply reply = new SurveyReply();

        survey.getChildren().add(reply);

        return nodeRepository.save(survey);
    }

    private Survey findSurvey(@PathVariable UUID id) {
        return nodeRepository.findById(id).filter(n->n instanceof Survey)
                .map(n->(Survey)n).orElseThrow(EntityNotFoundException::new);
    }
}
