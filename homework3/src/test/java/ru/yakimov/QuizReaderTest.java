package ru.yakimov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.props.QuizProps;
import ru.yakimov.services.QuestionGenerator;
import ru.yakimov.services.QuizReader;
import ru.yakimov.services.QuizReaderCsv;

/**
 * stub for more advanced testing
 * 
 * @author pavel
 *
 */
@SpringBootTest
@DisplayName("QuizReaderTest")
public class QuizReaderTest {

	@Autowired
	private QuizReader quizReader;

	/**
	 * we may just import this one
	 */
	@MockBean
	private QuestionGenerator questionGenerator;

	@MockBean
	private QuizProps quizProps;

	@Test
	public void readQuizTest() throws LoadQuestionsException {
		Mockito.when(quizProps.getLocalizedFilename()).thenReturn("testquiz_en.csv");

		Mockito.when(questionGenerator.generate("Are we just part of testing?, yes"))// also check if read line is correct one
				.thenReturn(Optional.of(new QuizQuestion("Are we just part of testing?", "yes", null)));

		List<QuizQuestion> qqList = quizReader.readQuestions();
		
		assertEquals(1, qqList.size());
		assertEquals("yes", qqList.get(0).getAnswer());
	}

	@Configuration
	@Import(QuizReaderCsv.class)
	public static class TestContextConfig {

	}
}
