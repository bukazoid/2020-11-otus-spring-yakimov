package ru.yakimov.runner;

import ru.yakimov.domain.ReadResourceException;

public interface Quiz {
	void proceedQuiz() throws ReadResourceException;
}