package ru.yakimov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.services.QuestionGenerator;

@DisplayName("QuestionGenerator test")
public class QuestionGeneratorTest {
	QuestionGenerator qGenerator;

	@BeforeEach
	void init() {
		// to reset state between tests if it has any
		qGenerator = new QuestionGenerator();
	}

	@DisplayName("question with choise")
	@Test
	public void normalQuestionTest() {
		Optional<QuizQuestion> question = qGenerator.generate("How are you?, fine, so so, perfect!");

		assertFalse(question.isEmpty());
		assertEquals("fine", question.get().getAnswer());
	}

	@DisplayName("empty line test")
	@Test
	public void emptyLineTest() {

		Optional<QuizQuestion> emptyLine = qGenerator.generate(" ");
		assertTrue(emptyLine.isEmpty());

	}

	@DisplayName("comment line test")
	@Test
	public void commentLineTest() {

		Optional<QuizQuestion> question = qGenerator.generate("#and now a good question");
		assertTrue(question.isEmpty());
	}
}
