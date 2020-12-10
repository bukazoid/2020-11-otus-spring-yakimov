package ru.yakimov.services;

import java.util.List;

import ru.yakimov.domain.QuizAnswer;
import ru.yakimov.domain.QuizQuestion;

public interface QuestionProcessor {

	List<QuizAnswer> getShuffledAnswers(QuizQuestion question);

//	void displayFreeAnswerQuestion(QuizQuestion question);
//
//	String displayQuestionWithOptionsAndReturnCorrectOne(QuizQuestion question);

}