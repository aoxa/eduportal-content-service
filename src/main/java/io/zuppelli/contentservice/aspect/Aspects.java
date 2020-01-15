package io.zuppelli.contentservice.aspect;

import io.zuppelli.contentservice.annotation.GenerateUUID;
import io.zuppelli.contentservice.annotation.UpdateDates;
import io.zuppelli.contentservice.model.Node;
import io.zuppelli.contentservice.model.NodeReply;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class Aspects {
    @Before("execution(* io.zuppelli.contentservice.repository.*.save(..))")
    public void updateIdAndCreation(JoinPoint joinPoint) throws Exception {
        Object entity = joinPoint.getArgs()[0];
        Class clazz = entity.getClass();

        Annotation[] annotations = clazz.getAnnotationsByType(GenerateUUID.class);

        if(annotations.length > 0) {
            GenerateUUID annotation = (GenerateUUID)annotations[0];

            if(null == clazz.getMethod(annotation.valueMethodName()).invoke(entity)) {
                clazz.getMethod(annotation.updateMethodName(), UUID.class).invoke(entity, UUID.randomUUID());
            }
        }

        annotations = clazz.getAnnotationsByType(UpdateDates.class);

        if(annotations.length > 0) {
            UpdateDates updateDates = (UpdateDates) annotations[0];

            if( updateDates.updateCreation() && null == clazz.getMethod("getCreationDate").invoke(entity) ) {
                clazz.getMethod("setCreationDate", Date.class).invoke(entity, new Date());
            } else if( updateDates.updateModification() ) {
                clazz.getMethod("setModificationDate", Date.class).invoke(entity, new Date());
            }

            if(entity instanceof Node) {
                updateMethodDate((Node)entity);
            }
        }
    }

    public void updateMethodDate(Node node) {
        node.getChildren().stream()
                .filter(reply->null==((NodeReply) reply).getCreationDate())
                .forEach(reply->((NodeReply) reply).setCreationDate(new Date()));

    }
}
