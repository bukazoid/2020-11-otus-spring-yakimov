package ru.yakimov.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionProcessor;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.UserIO;
import ru.yakimov.services.WelcomeMessage;

@AllArgsConstructor
public class QuizJustDisplay implements Quiz {
	final private QuizReader quizReader;
	final private UserIO userIO;
	final private QuestionProcessor processor;
	final private WelcomeMessage welcome;


	@Override
	public void proceedQuiz() {

		welcome.sayHello();

		// say hello
		userIO.printLine("Hello, student! let's see our questions");
		userIO.printLine();

		List<QuizQuestion> questions = quizReader.readQuestions();
		userIO.printLine("we have %s questions", questions.size());

		for (QuizQuestion question : questions) {
			if (question.isFreeAnswer()) {
				processor.displayFreeAnswerQuestion(question);
			} else {
				processor.displayQuestionWithOptionsAndReturnCorrectOne(question);
			}
			userIO.printLine();
		}

		userIO.printLine("done");
	}

}
