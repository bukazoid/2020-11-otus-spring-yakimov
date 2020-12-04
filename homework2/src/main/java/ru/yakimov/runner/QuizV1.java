package ru.yakimov.runner;

import java.util.List;

import lombok.AllArgsConstructor;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionProcessor;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.UserIO;

@AllArgsConstructor
public class QuizV1 implements Quiz {
	final private QuizReader quizReader;
	final private UserIO userIO;
	final private QuestionProcessor processor;

	@Override
	public void proceedQuiz() {

		// intro
		userIO.printLine("#".repeat(20));
		userIO.printLine("Welcome to our quiz!");
		userIO.printLine("#".repeat(20));
		userIO.printLine();

		// say hello
		userIO.printLine("Hello, student! let's see ouq questions");
		userIO.printLine();

		List<QuizQuestion> questions = quizReader.readQuestions();
		for (QuizQuestion question : questions) {
			processor.processQuestion(question);
			userIO.printLine();
		}

	}

}
