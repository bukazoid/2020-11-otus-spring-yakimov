package ru.yakimov.runner;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionProcessor;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.UserIO;
import ru.yakimov.services.WelcomeMessage;

@AllArgsConstructor
public class QuizWithUserAnwers implements Quiz {
	final private QuizReader quizReader;
	final private UserIO userIO;
	final private QuestionProcessor processor;
	final private WelcomeMessage welcome;

	final private int requiredPercent;

	@Override
	public void proceedQuiz() {

		welcome.sayHello();

		userIO.printText("To continue, Please enter your name: ");
		String name = userIO.readInput();
		userIO.printLine();

		// say hello
		userIO.printLine("Hello, student, '%s'! Be ready to check your knowledge.", name);
		userIO.printLine();

		int correct = 0;
		List<QuizQuestion> questions = quizReader.readQuestions();
		Collections.shuffle(questions);
		for (QuizQuestion question : questions) {
			if (question.isFreeAnswer()) {
				processor.displayFreeAnswerQuestion(question);
				userIO.printText("Enter your answer: ");
				String answer = userIO.readInput();
				if (answer.equalsIgnoreCase(question.getAnswer())) {
					correct++;
				}

			} else {
				String correctAnswer = processor.displayQuestionWithOptionsAndReturnCorrectOne(question);
				userIO.printText("Pick your answer: ");

				String userAnswer = userIO.readInput();
				if (correctAnswer.equalsIgnoreCase(userAnswer)) {
					correct++;
				}
			}

			userIO.printLine();
		}

		userIO.printLine();
		float resultPercent = 100f * correct / questions.size();
		userIO.printLine("Your result is %s out of %s.\n\tYou you have %.2f%% of correct answers", correct,
				questions.size(), resultPercent);

		if (resultPercent > requiredPercent) {
			userIO.printLine("Since you have enough correct answers test is passed");
		} else {
			userIO.printLine("Test is not passed, try later");
		}

	}

}
