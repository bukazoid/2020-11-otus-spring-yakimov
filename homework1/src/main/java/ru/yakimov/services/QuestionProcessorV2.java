package ru.yakimov.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.yakimov.domain.QuizQuestion;

/**
 * for possible future
 * @author pavel
 *
 */
public class QuestionProcessorV2 implements QuestionProcessor {
	final private UserIO userIO;

	public QuestionProcessorV2(UserIO shell) {
		this.userIO = shell;
	}

	public boolean processQuestion(QuizQuestion question) {
		userIO.printLine(question.getQuestion());

		if (question.getAlternativeAnswers().isEmpty()) {
			userIO.printText("Enter your answer: ");
			String answer = userIO.readInput();
			return answer.equalsIgnoreCase(question.getAnswer());
		}

		List<String> answers = new ArrayList<String>(question.getAlternativeAnswers());
		answers.add(question.getAnswer());

		Collections.shuffle(answers);
		for (int i = 0; i < answers.size(); i++) {// i know it is not pretty
			userIO.printLine((i + 1) + " - " + answers.get(i));
		}

		do {
			userIO.printText("Pick your answer: ");
			String answerNumber = userIO.readInput();

			try {
				int index = Integer.parseInt(answerNumber);
				String studentAnswer = answers.get(index - 1);
				return question.getAnswer().equalsIgnoreCase(studentAnswer);
			} catch (Exception ex) {
				// ignore
			}
			userIO.printLine(String.format("Please enter the correct number form %s till %s", 1, answers.size() + 1));
		} while (true);// yeah, eternal loop, but it is ok here

	}
}
