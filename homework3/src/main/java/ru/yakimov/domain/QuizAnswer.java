package ru.yakimov.domain;

import lombok.Data;

@Data
public class QuizAnswer {
	final private String answer;
	/**
	 * character or digit
	 */
	final private String answerProjection; // trouble with naming
}
