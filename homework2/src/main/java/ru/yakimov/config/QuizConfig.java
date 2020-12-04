package ru.yakimov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import ru.yakimov.runner.Quiz;
import ru.yakimov.runner.QuizWithUserAnwers;
import ru.yakimov.services.QuestionProcessor;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.UserIO;
import ru.yakimov.services.WelcomeMessage;

@PropertySource("classpath:application.properties")
@Configuration
public class QuizConfig {

	@Bean
	public Quiz getQuiz(QuizReader qReader, UserIO userIO, QuestionProcessor qProcessor, WelcomeMessage welcome,
			@Value("${requiredPercent}") int requiredPercent) {
		// may use QuizJustDisplay to just return list of questions		
		return new QuizWithUserAnwers(qReader, userIO, qProcessor, welcome, requiredPercent);// too many parameters
	}
}
