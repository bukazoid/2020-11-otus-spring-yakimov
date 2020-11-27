package ru.yakimov.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.yakimov.domain.QuizQuestion;

public class QuestionProcessor {
	Shell shell;

	public QuestionProcessor(Shell shell) {
		this.shell = shell;
	}

	public boolean processQuestion(QuizQuestion question) {
		shell.printLine(question.getQuestion());

		if (question.getAlternativeAnswers().isEmpty()) {
			shell.printText("Enter your answer: ");
			String answer = shell.readInput();
			return answer.equalsIgnoreCase(question.getAnswer());
		}

		List<String> answers = new ArrayList<String>(question.getAlternativeAnswers());
		answers.add(question.getAnswer());

		Collections.shuffle(answers);
		for (int i = 0; i < answers.size(); i++) {// i know it is not pretty
			shell.printLine((i + 1) + " - " + answers.get(i));
		}

		do {
			shell.printText("Pick your answer: ");
			String answerNumber = shell.readInput();

			try {
				int index = Integer.parseInt(answerNumber);
				String studentAnswer = answers.get(index - 1);
				return question.getAnswer().equalsIgnoreCase(studentAnswer);
			} catch (Exception ex) {
				// ignore
			}
			shell.printLine(String.format("Please enter the correct number form %s till %s", 1, answers.size() + 1));
		} while (true);// yeah, eternal loop, but it is ok here

	}
}
