package ru.yakimov.services;

import ru.yakimov.domain.QuizQuestion;

public interface QuestionProcessor {
	boolean processQuestion(QuizQuestion question);
}