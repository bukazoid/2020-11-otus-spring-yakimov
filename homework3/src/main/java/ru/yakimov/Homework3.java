package ru.yakimov;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.props.QuizProps;
import ru.yakimov.services.Quiz;

@EnableConfigurationProperties(QuizProps.class)
@SpringBootApplication
public class Homework3 {

	public static void main(String[] args) throws BeansException, LoadQuestionsException {
		ConfigurableApplicationContext context = SpringApplication.run(Homework3.class, args);

		context.getBean(Quiz.class).proceedQuiz();
	}

}
