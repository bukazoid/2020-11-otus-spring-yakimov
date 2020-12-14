package ru.yakimov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.yakimov.domain.LoadQuestionsException;
import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.props.QuizProps;
import ru.yakimov.services.QuizReader;

/**
 * stub for more advanced testing
 * 
 * @author pavel
 *
 */
@SpringBootTest
@DisplayName("AppTest")
public class AppTest {

	AutoCloseable mock2close;

	@BeforeEach
	public void setUp() throws Exception {
		mock2close = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void destroy() throws Exception {
		mock2close.close();
	}

	@Autowired
	QuizReader quizReader;

	@Test
	public void readQuizTest() throws LoadQuestionsException {
		List<QuizQuestion> qqList = quizReader.readQuestions();
		assertEquals(1, qqList.size());
	}

	@EnableConfigurationProperties(QuizProps.class)
	@Configuration
	@ComponentScan({ "ru.yakimov.config", "ru.yakimov.services", "ru.yakimov.props" })
	public static class TestContextConfig {

	}
}
