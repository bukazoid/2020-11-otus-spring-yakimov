package ru.yakimov.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.domain.QuizQuestion;

@Slf4j
public class QuestionGenerator {
	final String CSV_SEPARATOR = ",";

	public Optional<QuizQuestion> generate(String line) {
		String[] params = line.split(CSV_SEPARATOR);
		if (params.length < 2) {
			log.info("not enough data for question, skip line: {}", line);
			// not enough data to read
			return Optional.empty();
		}

		String question = params[0];
		String answer = params[1].trim();
		List<String> alternatives = Arrays.asList(params).subList(2, params.length).stream().map(s -> s.trim())
				.collect(Collectors.toList());

		return Optional.of(new QuizQuestion(question, answer, alternatives));
	}
}
