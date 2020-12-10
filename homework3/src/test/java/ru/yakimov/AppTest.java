package ru.yakimov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.yakimov.domain.QuizQuestion;
import ru.yakimov.domain.ReadResourceException;
import ru.yakimov.props.QuizProps;
import ru.yakimov.services.QuizReader;

/**
 * stub for more advanced testing
 * 
 * @author pavel
 *
 */
@SpringBootTest
@DisplayName("AppЕest")
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
	public void readQuizTest() throws ReadResourceException {
		List<QuizQuestion> qqList = quizReader.readQuestions();
		System.out.println("qqList: " + qqList);
		assertEquals(1, qqList.size());
	}

	@Configuration
	@EnableAutoConfiguration
	@ComponentScan({ "ru.yakimov.config", "ru.yakimov.services" })
	public static class TestContextConfig {

		@Bean
		QuizProps props() {// WHYYYYYYY. without this bean i have an error
			return new QuizProps("testquiz", 61, new Locale("en"));
		}
	}
}
