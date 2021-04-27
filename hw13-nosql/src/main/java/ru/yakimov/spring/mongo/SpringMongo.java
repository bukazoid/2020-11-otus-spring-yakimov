package ru.yakimov.spring.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.cloudyrock.spring.v5.EnableMongock;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class SpringMongo {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongo.class, args);
	}

}
