package io.zuppelli.contentservice;

import io.zuppelli.contentservice.model.Article;
import io.zuppelli.contentservice.model.Comment;
import io.zuppelli.contentservice.model.Node;
import io.zuppelli.contentservice.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ContentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentServiceApplication.class, args);
	}
}
