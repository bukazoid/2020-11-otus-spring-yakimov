package ru.yakimov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import ru.yakimov.props.QuizProps;

@EnableConfigurationProperties(QuizProps.class)
@SpringBootApplication
public class Homework3Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework3Application.class, args);
	}

}
