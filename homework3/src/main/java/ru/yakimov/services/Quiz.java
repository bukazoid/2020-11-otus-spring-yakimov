package ru.yakimov.services;

import ru.yakimov.domain.LoadQuestionsException;

public interface Quiz {
	void proceedQuiz() throws LoadQuestionsException;
}