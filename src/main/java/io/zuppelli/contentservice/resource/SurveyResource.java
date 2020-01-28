package io.zuppelli.contentservice.resource;

import io.zuppelli.contentservice.exception.EntityNotFoundException;
import io.zuppelli.contentservice.model.*;
import io.zuppelli.contentservice.model.partial.Element;
import io.zuppelli.contentservice.model.partial.InputText;
import io.zuppelli.contentservice.model.partial.Option;
import io.zuppelli.contentservice.model.partial.Select;
import io.zuppelli.contentservice.repository.NodeRepository;
import io.zuppelli.contentservice.resource.dto.ArticleDTO;
import io.zuppelli.contentservice.resource.dto.CommentDTO;
import io.zuppelli.contentservice.resource.dto.ReplyDTO;
import io.zuppelli.contentservice.resource.dto.SurveyDTO;
import io.zuppelli.contentservice.service.Builder;
import io.zuppelli.contentservice.service.node.SurveyScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surveys")
public class SurveyResource {
    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private SurveyScoreService surveyScoreService;

    @GetMapping
    public List<Node<?>> list() {
        return nodeRepository.findAllByType(Survey.TYPE);
    }

    @PostMapping()
    public Survey add(@RequestBody @Valid SurveyDTO dto) {
        Builder<Survey> builder = Survey.getBuilder().add("description", dto.getDescription())
                .add("title", dto.getTitle())
                .add("limitDate", dto.getExpiry());
        builder.add("elements", dto.getElements().stream()
            .map((element)->{
                if("text".equals(element.getType())) {
                    Builder<? extends Element> elementBuilder = Element.builder(InputText.class);

                    elementBuilder.add("tip", element.getTip());
                    elementBuilder.add("title", element.getTitle());
                    elementBuilder.add("name", element.getName());
                    return elementBuilder.build();
                }

                Builder<? extends Element> elementBuilder = Element.builder(Select.class);

                elementBuilder.add("tip", element.getTip());
                elementBuilder.add("title", element.getTitle());
                elementBuilder.add("name", element.getName());

                elementBuilder.add("checkBox", "checkbox".equals(element.getType()));
                elementBuilder.add("radioButton", "radio".equals(element.getType()));
                elementBuilder.add("multivalued", "select-multi".equals(element.getType()));

                elementBuilder.add("options",
                    element.getValues().stream().map((valuedto)->{
                        Builder optionBuilder = Element.builder(Option.class)
                                .add("value", valuedto.getOption())
                                .add("selected", valuedto.isSelected());
                        if(!StringUtils.isBlank(valuedto.getName())) optionBuilder.add("name", valuedto.getName());

                        return optionBuilder.build();
                    }).collect(Collectors.toSet()));

                return elementBuilder.build();
            }).collect(Collectors.toList()));

        return nodeRepository.save(builder.build());
    }

    @GetMapping("/{id}")
    public Node get(@PathVariable UUID id) {
        return nodeRepository.findById(id).filter(n->n instanceof Survey).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/{id}")
    public Survey edit(@PathVariable UUID id, @RequestBody @Valid SurveyDTO dto) {
        Survey survey = findSurvey(id);
        survey.setBody(dto.getDescription());
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
        Builder<SurveyReply> builder = SurveyReply.builder().add("elements", collectElements(dto) );

        SurveyReply reply = builder.build();

        surveyScoreService.decorateScore(survey, reply);

        survey.addChild(reply);

        return nodeRepository.save(survey);
    }

    private Set<Element> collectElements(@RequestBody @Valid ReplyDTO dto) {
        return dto.getReplies().stream().map(item->{
            Element element;
            if("text".equals(item.getType())) {
                InputText el = new InputText();
                el.setName(item.getName());
                el.setValue(item.getValue());

                element = el;
            } else {
                Select el = new Select();
                el.setName(item.getName());
                el.setOptions(item.getValues()
                        .stream().map((option)->{
                            Option op = new Option();
                            op.setName(option);
                            return op;
                        })
                        .collect(Collectors.toSet()));

                element = el;
            }

            return element;
        }).collect(Collectors.toSet());
    }

    private Survey findSurvey(@PathVariable UUID id) {
        return nodeRepository.findById(id).filter(n->n instanceof Survey)
                .map(n->(Survey)n).orElseThrow(EntityNotFoundException::new);
    }
}
