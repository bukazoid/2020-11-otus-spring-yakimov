package ru.yakimov.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.yakimov.domain.QuizQuestion;

/**
 * just output questions
 * 
 * @author pavel
 *
 */
public class QuestionProcessorV1 implements QuestionProcessor {
	UserIO userIO;

	public QuestionProcessorV1(UserIO shell) {
		this.userIO = shell;
	}

	public boolean processQuestion(QuizQuestion question) {
		userIO.printLine(question.getQuestion());

		if (question.getAlternativeAnswers().isEmpty()) {
			return true;
		}

		List<String> answers = new ArrayList<String>(question.getAlternativeAnswers());
		answers.add(question.getAnswer());

		Collections.shuffle(answers);
		for (int i = 0; i < answers.size(); i++) {// i know it is not pretty
			userIO.printLine((i + 1) + " - " + answers.get(i));
		}

		return true;// has no meaning there
	}
}
