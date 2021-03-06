package ru.yakimov.spring.mvc.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mvc.domain.Author;
import ru.yakimov.spring.mvc.repositories.AuthorRepository;

@Slf4j
@DataJpaTest
@DisplayName("AuthorDaoTest")
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // overkill but maybe helpful, should be careful with
//																// tests changing context
public class AuthorRepositoryTest {
	@Autowired
	private AuthorRepository repo;

	@DisplayName("should return 2")
	@Test
	public void shouldReturnCount() {
		assertEquals(2, repo.count());
	}

	@DisplayName("should read Homer")
	@Test
	public void shouldReturnHomer() {
		String name = repo.findById(1L).map(Author::getName).orElse(null);
		assertEquals("Homer", name);
	}

	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		List<Author> authors = repo.findAll();

		assertThat(authors).hasSize(2).containsExactlyInAnyOrderElementsOf(
				Arrays.asList(new Author(1L, "Homer"), new Author(2L, "Shakespeare")));

	}

	final static private String AUTHOR_TO_ADD = "Student";

//	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)  can be helpful
	@Test
	@DisplayName("should add Student")
	void shouldInsert() {
		repo.save(new Author(null, AUTHOR_TO_ADD));

		List<Author> authors = repo.findAll();

		log.info("authors: {}", authors);

//		final static private long NEXT_AUTHOR_ID = 3;// can be changed, also could be another id generator
//		assertThat(authors).hasSize(3).contains(new Author(NEXT_AUTHOR_ID, AUTHOR_TO_ADD));// btw: better to not check
//																							// id

		assertThat(authors).hasSize(3).filteredOn(a -> a.getName().equals(AUTHOR_TO_ADD)).hasSize(1);

	}
}
