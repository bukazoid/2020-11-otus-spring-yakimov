package ru.yakimov;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.yakimov.runner.Quiz;
import ru.yakimov.runner.QuizV1;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

		Quiz quiz = context.getBean(QuizV1.class);
		quiz.proceedQuiz();
		
		context.close();
	}
}
