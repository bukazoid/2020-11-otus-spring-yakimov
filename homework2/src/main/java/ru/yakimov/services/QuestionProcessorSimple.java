package ru.yakimov.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import ru.yakimov.domain.QuizQuestion;

/**
 * just output questions
 * 
 * @author pavel
 *
 */
@Service
public class QuestionProcessorSimple implements QuestionProcessor {
	final private UserIO userIO;

	public QuestionProcessorSimple(UserIO shell) {
		this.userIO = shell;
	}

	public void displayFreeAnswerQuestion(QuizQuestion question) {
		userIO.printLine(question.getQuestion());
	}

	public String displayQuestionWithOptionsAndReturnCorrectOne(QuizQuestion question) {
		userIO.printLine(question.getQuestion());

		String correct = "";

		List<String> options = new ArrayList<String>(question.getAlternativeAnswers());
		options.add(question.getAnswer());

		Collections.shuffle(options);
		for (int i = 0; i < options.size(); i++) {// i know it is not pretty
			userIO.printLine((i + 1) + " - " + options.get(i));

			if (question.getAnswer().equals(options.get(i))) {
				correct = "" + (i + 1);
			}
		}

		return correct;
	}
}
