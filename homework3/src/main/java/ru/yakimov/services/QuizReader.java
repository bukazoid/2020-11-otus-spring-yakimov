package ru.yakimov.services;

import java.util.List;

import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.domain.ReadResourceException;

public interface QuizReader {

	List<QuizQuestion> readQuestions() throws ReadResourceException;

}