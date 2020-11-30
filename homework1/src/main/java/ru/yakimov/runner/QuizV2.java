package ru.yakimov.runner;

import java.util.List;

import lombok.AllArgsConstructor;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionProcessorV1;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.UserIO;

@AllArgsConstructor
public class QuizV2 implements Quiz {
	QuizReader quizReader;
	UserIO userIO;
	QuestionProcessorV1 processor;

	@Override
	public void proceedQuiz() {

		// intro
		userIO.printLine("#".repeat(20));
		userIO.printLine("Welcome to our quiz!");
		userIO.printLine("#".repeat(20));
		userIO.printLine();
		userIO.printText("To continue, Please enter your name: ");
		String name = userIO.readInput();
		userIO.printLine();

		// say hello
		userIO.printLine("Hello, student, '%s'! Be ready to check your knowledge.", name);
		userIO.printLine();

		int correct = 0;
		List<QuizQuestion> questions = quizReader.readQuestions();
		for (QuizQuestion question : questions) {
			if (processor.processQuestion(question)) {
				correct++;
			}
			userIO.printLine();
		}

		userIO.printLine();
		userIO.printLine("Your result is %s out of %s.\n\tYou you have %.2f%% of correct answers", correct,
				questions.size(), 100f * correct / questions.size());

	}

}
