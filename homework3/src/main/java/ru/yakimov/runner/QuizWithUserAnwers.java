package ru.yakimov.runner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.yakimov.domain.QuizAnswer;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.domain.ReadResourceException;
import ru.yakimov.props.QuizProps;
import ru.yakimov.services.QMessages;
import ru.yakimov.services.QuestionProcessor;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.UserIO;
import ru.yakimov.services.WelcomeMessageService;

@RequiredArgsConstructor
@Service
public class QuizWithUserAnwers implements Quiz, CommandLineRunner {
	final private QuizReader quizReader;
	final private UserIO userIO;
	final private QuestionProcessor processor;
	final private WelcomeMessageService welcome;

	final private QuizProps props;

	final private QMessages messages;

	@Override
	public void proceedQuiz() throws ReadResourceException {

		welcome.sayHello();

		userIO.printText(messages.get("to-continue-enter-name") + " ");
		String name = userIO.readInput();
		userIO.printLine();

		// say hello
		userIO.printLine(messages.get("hello-and-ready", name));
		userIO.printLine();

		int correct = 0;
		List<QuizQuestion> questions = quizReader.readQuestions();
		Collections.shuffle(questions);
		for (QuizQuestion question : questions) {
			userIO.printLine(question.getQuestion());

			if (question.isFreeAnswer()) {
				userIO.printText(messages.get("enter-answer"));
				String answer = userIO.readInput();
				if (answer.equalsIgnoreCase(question.getAnswer())) {
					correct++;
				}

			} else {
				List<QuizAnswer> answers = processor.getShuffledAnswers(question);
				for (QuizAnswer answer : answers) {
					userIO.printLine(answer.getAnswerProjection() + " - " + answer.getAnswer());
				}

				// check if answer exists
				Optional<QuizAnswer> foundQAnswer = Optional.empty();
				do {

					userIO.printText(messages.get("pick-answer") + " ");
					String userAnswer = userIO.readInput();

					foundQAnswer = answers.stream().filter(a -> a.getAnswerProjection().equals(userAnswer)).findFirst();
					if (foundQAnswer.isEmpty()) {
						userIO.printLine(messages.get("pick-answer-from-possible-options"));
					}
				} while (foundQAnswer.isEmpty());

				if (foundQAnswer.get().getAnswer().equalsIgnoreCase(question.getAnswer())) {
					correct++;
					// we may say - it is correct
				}
			}

			userIO.printLine();
		}

		userIO.printLine();
		float resultPercent = 100f * correct / questions.size();
		userIO.printLine(
				messages.get("your-result", correct, questions.size(), floatWith2SymbolsAfterDot(resultPercent)));

		if (resultPercent > props.getRequiredPercent()) {
			userIO.printLine(messages.get("test-passed", name));
		} else {
			userIO.printLine(messages.get("test-not-passed", name));
		}

	}

	private String floatWith2SymbolsAfterDot(float value) {
		return String.format("%.2f", value);
	}

	@Override
	public void run(String... args) throws Exception {
		proceedQuiz();
	}

}
