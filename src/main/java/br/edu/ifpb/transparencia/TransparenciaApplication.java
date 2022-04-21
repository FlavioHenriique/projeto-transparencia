package br.edu.ifpb.transparencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "br.edu.ifpb.transparencia.repository")
public class TransparenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransparenciaApplication.class, args);
	}

}
