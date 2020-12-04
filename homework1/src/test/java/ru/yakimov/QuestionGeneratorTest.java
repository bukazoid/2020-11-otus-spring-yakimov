package ru.yakimov;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionGenerator;

@Slf4j
public class QuestionGeneratorTest {
	@Rule
	public TestName name = new TestName();

	@Test
	public void normalQuestionTest() {
		log.info("test: {}", name.getMethodName());

		QuestionGenerator qGenerator = new QuestionGenerator();

		Optional<QuizQuestion> question = qGenerator.generate("How are you?, fine, so so, perfect!");
		assertFalse(question.isEmpty());
		assertEquals("fine", question.get().getAnswer());
	}

	@Test
	public void commentEmptyLineTest() {
		log.info("test: {}", name.getMethodName());

		QuestionGenerator qGenerator = new QuestionGenerator();

		Optional<QuizQuestion> question = qGenerator.generate("#and now a good question");
		assertTrue(question.isEmpty());
		
		Optional<QuizQuestion> emptyLine = qGenerator.generate(" ");
		assertTrue(emptyLine.isEmpty());

	}
}
