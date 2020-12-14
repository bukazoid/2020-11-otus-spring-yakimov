package ru.yakimov.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.yakimov.domain.QuizAnswer;
import ru.yakimov.domain.QuizQuestion;

/**
 * just output questions
 * 
 * @author pavel
 *
 */
@Service
public class QuestionProcessorSimple implements QuestionProcessor {

	@Override
	public List<QuizAnswer> getShuffledAnswers(QuizQuestion question) {
		if (question.isFreeAnswer()) {
			return null;
		}

		List<String> options = new ArrayList<String>(question.getAlternativeAnswers());
		options.add(question.getAnswer());

		Collections.shuffle(options);

		AtomicInteger num = new AtomicInteger(0);// some int container
		// not too hard to make letters, like: 'a' + num.getAndIncrement()
		return options.stream().map(a -> new QuizAnswer(a, "" + num.incrementAndGet())).collect(Collectors.toList());
	}
}
