package ru.yakimov.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.domain.QuizQuestion;

@Slf4j
public class QuizReader {

	final String quizFile;
	final QuestionGenerator quizGenerator;

	public QuizReader(String quizFile, QuestionGenerator quizGenerator) {
		this.quizFile = quizFile;
		this.quizGenerator = quizGenerator;
	}

	public List<QuizQuestion> readQuestions() {
		List<QuizQuestion> questions = new ArrayList<>();
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(quizFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#") || line.isBlank()) {
					// skip comment or empty line
					continue;
				}

				QuizQuestion qq = quizGenerator.generate(line);
				if (qq == null) {
					log.error("can't read question from line: {} ", line);
				} else {
					questions.add(qq);
				}
			}

			return questions;
		} catch (Exception e) {// it could be null pointer here
			log.error("can't  read resource, {}", quizFile, e);
		}
		return Collections.emptyList();
	}
}
