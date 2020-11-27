package ru.yakimov.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizQuestion {
	final String question;
	/**
	 * correct answer
	 */
	final String answer;
	/**
	 * if it is empty then it is a question with free answer
	 */
	final List<String> alternativeAnswers;

}
