package ru.yakimov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.yakimov.runner.Quiz;

@Configuration
@ComponentScan
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		Quiz quiz = context.getBean(Quiz.class);
		quiz.proceedQuiz();

		context.close();
	}
}
