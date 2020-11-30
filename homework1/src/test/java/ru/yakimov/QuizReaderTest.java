package ru.yakimov;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionGenerator;
import ru.yakimov.services.QuizReaderCsv;

@Slf4j
public class QuizReaderTest {
	@Rule
	public TestName name = new TestName();

	@Test
	public void readFileTest() {
		log.info("test: {}", name.getMethodName());

		List<QuizQuestion> questions = new QuizReaderCsv("testquiz.csv", new QuestionGenerator()).readQuestions();

		// uncomment next line to get more info
		// log.info("questions: {}", questions);
		assertNotNull(questions);
		assertEquals(1, questions.size());
	}
}
