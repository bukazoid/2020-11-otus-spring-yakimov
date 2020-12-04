package ru.yakimov.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.domain.QuizQuestion;

@Service
@Slf4j
public class QuizReaderCsv implements QuizReader {

	final private String quizFile;
	final private QuestionGenerator quizGenerator;

	public QuizReaderCsv(@Value("${quizFile}") String quizFile, QuestionGenerator quizGenerator) {
		this.quizFile = quizFile;
		this.quizGenerator = quizGenerator;
	}

	@Override
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

				Optional<QuizQuestion> qq = quizGenerator.generate(line);
				qq.ifPresent(questions::add);
			}

			return questions;
		} catch (Exception e) {// it could be null pointer here
			log.error("can't  read resource, {}", quizFile, e);
		}
		return Collections.emptyList();
	}
}
