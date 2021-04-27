package ru.yakimov.spring.mongo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mongo.domain.Author;
import ru.yakimov.spring.mongo.events.EventHandlerAuthor;
import ru.yakimov.spring.mongo.repositories.AuthorRepository;

@Slf4j
@DataMongoTest
@DisplayName("AuthorDaoTest")
@Import(EventHandlerAuthor.class)
public class AuthorRepositoryTest {
	// XXX: can be useful one day
	//	@TestConfiguration
	//	public static class TestContextConfiguration {
	//		@Bean
	//		public EventHandlerAuthor eventHandler(BookRepository bookRepo) {
	//			return new EventHandlerAuthor(bookRepo);
	//		}
	//	}

	String HOMER_NAME = "Homer";
	String HOME_ID = "1";

	@Autowired
	private AuthorRepository repo;

	@DisplayName("should read Homer")
	@Test
	public void shouldReturnHomer() {
		Author author = repo.findById(HOME_ID).orElse(null);
		assertNotNull(author);
		assertEquals(HOMER_NAME, author.getName());
	}

	final static private String AUTHOR_TO_ADD = "Student";

	@Test
	@DisplayName("should add Student")
	void shouldInsert() {
		repo.save(new Author(null, AUTHOR_TO_ADD));

		List<Author> authors = repo.findAll();

		log.info("authors: {}", authors);

		assertThat(authors).hasSize(4).filteredOn(a -> a.getName().equals(AUTHOR_TO_ADD)).hasSize(1);
	}

	@Test
	@DisplayName("Delete author with book")
	void shouldAbortDeletion() {
		Author homer = repo.findById(HOME_ID).orElse(null);
		assertNotNull(homer);
 
		assertThrows(RuntimeException.class, () -> repo.delete(homer));
	}

}
