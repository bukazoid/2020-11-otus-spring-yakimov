package ru.yakimov.services;

import ru.yakimov.domain.QuizQuestion;

public interface QuestionProcessor {
	
	void displayFreeAnswerQuestion(QuizQuestion question);

	String displayQuestionWithOptionsAndReturnCorrectOne(QuizQuestion question);

}