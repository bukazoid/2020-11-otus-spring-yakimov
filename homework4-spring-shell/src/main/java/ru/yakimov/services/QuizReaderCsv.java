package ru.yakimov.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.props.QuizProps;

@Service
public class QuizReaderCsv implements QuizReader {

	private final QuizProps props;
	private final QuestionGenerator quizGenerator;

	public QuizReaderCsv(QuestionGenerator quizGenerator, QuizProps props) {
		this.props = props;
		this.quizGenerator = quizGenerator;
	}

	@Override
	public List<QuizQuestion> readQuestions() throws LoadQuestionsException {
		return readResource(props.getLocalizedFilename());
	}

	private List<QuizQuestion> readResource(String name) throws LoadQuestionsException {
		List<QuizQuestion> questions = new ArrayList<>();
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(name);
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
			throw new LoadQuestionsException(name, e);
		}
	}
}
