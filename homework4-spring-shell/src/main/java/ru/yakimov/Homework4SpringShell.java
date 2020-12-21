package ru.yakimov;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.props.QuizProps;

@EnableConfigurationProperties(QuizProps.class)
@SpringBootApplication
public class Homework4SpringShell {

	public static void main(String[] args) throws BeansException, LoadQuestionsException {
		ConfigurableApplicationContext context = SpringApplication.run(Homework4SpringShell.class, args);
	}

}
