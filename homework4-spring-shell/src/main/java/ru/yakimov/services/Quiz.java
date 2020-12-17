package ru.yakimov.services;

import ru.yakimov.domain.LoadQuestionsException;

public interface Quiz {
	void proceedQuiz() throws LoadQuestionsException;

	void proceedRandomQuestion(String name) throws LoadQuestionsException;

	void proceedQuiz(String name) throws LoadQuestionsException;
}