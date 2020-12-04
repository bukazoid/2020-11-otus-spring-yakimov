package ru.yakimov.services;

import java.util.List;

import ru.yakimov.domain.QuizQuestion;

public interface QuizReader {

	List<QuizQuestion> readQuestions();

}