package ru.yakimov.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.domain.QuizAnswer;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.props.QuizProps;

@Service
@AllArgsConstructor
public class QuizWithUserAnwers implements Quiz {
	final private QuizReader quizReader;
	final private LocalizedIOService ioService;
	final private QuestionProcessor processor;
	final private WelcomeMessageService welcomeService;

	final private QuizProps props;

	@Override
	public void proceedQuiz() throws LoadQuestionsException {

		welcomeService.sayHello();

		String name = getUsername();

		// say hello
		ioService.printLocalizedLine("hello-and-ready", name);

		ioService.printLine();

		List<QuizQuestion> questions = quizReader.readQuestions();
		int correctAnswers = proceedQuestions(questions);

		ioService.printLine();

		printResults(name, questions.size(), correctAnswers);

	}

	private void printResults(String name, int questionCount, int correctAnswers) {
		float resultPercent = 100f * correctAnswers / questionCount;
		ioService.printLocalizedLine("your-result", correctAnswers, questionCount,
				floatWith2SymbolsAfterDot(resultPercent));

		if (resultPercent > props.getRequiredPercent()) {
			ioService.printLocalizedLine("test-passed", name);
		} else {
			ioService.printLocalizedLine("test-not-passed", name);
		}
	}

	private int proceedQuestions(List<QuizQuestion> questions) {
		int correct = 0;
		Collections.shuffle(questions);

		for (QuizQuestion question : questions) {

			if (question.isFreeAnswer()) {
				if (processQuestionWithFreeAnswer(question)) {
					correct++;
				}

			} else {
				if (processQuestionWithOptions(question)) {
					correct++;
				}

			}

			ioService.printLine();
		}

		return correct;
	}

	private boolean processQuestionWithFreeAnswer(QuizQuestion question) {
		ioService.printLine(question.getQuestion());
		ioService.printLocalizedText("enter-answer");
		String answer = ioService.readInput();

		return answer.equalsIgnoreCase(question.getAnswer());
	}

	private boolean processQuestionWithOptions(QuizQuestion question) {
		ioService.printLine(question.getQuestion());
		List<QuizAnswer> answers = processor.getShuffledAnswers(question);
		for (QuizAnswer answer : answers) {
			ioService.printLine(answer.getAnswerProjection() + " - " + answer.getAnswer());
		}

		// check if answer exists
		Optional<QuizAnswer> foundQAnswer = Optional.empty();
		do {

			ioService.printLocalizedText("pick-answer");
			String userAnswer = ioService.readInput();

			foundQAnswer = answers.stream().filter(a -> a.getAnswerProjection().equals(userAnswer)).findFirst();
			if (foundQAnswer.isEmpty()) {
				ioService.printLocalizedLine("pick-answer-from-possible-options");
			}
		} while (foundQAnswer.isEmpty());

		return foundQAnswer.get().getAnswer().equalsIgnoreCase(question.getAnswer());
	}

	private String getUsername() {
		ioService.printLocalizedText("to-continue-enter-name");
		String name = ioService.readInput();
		ioService.printLine();
		return name;
	}

	private String floatWith2SymbolsAfterDot(float value) {
		return String.format("%.2f", value);
	}

}
