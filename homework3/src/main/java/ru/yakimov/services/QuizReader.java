package ru.yakimov.services;

import java.util.List;

import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.domain.LoadQuestionsException;

public interface QuizReader {

	List<QuizQuestion> readQuestions() throws LoadQuestionsException;

}