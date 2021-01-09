package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.yakimov.spring.db.domain.Author;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("AuthorDaoTest")
public class AuthorDaoJdbcTest {
	@Autowired
	private AuthorDao dao;

	@DisplayName("should return 2")
	@Test
	public void shouldReturnCount() {
		assertEquals(2, dao.count());
	}

	@DisplayName("should read Homer")
	@Test
	public void shouldReturnHomer() {
		String name = Optional.ofNullable(dao.read(1L)).map(Author::getName).orElse(null);
		assertEquals("Homer", name);
	}

	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		List<Author> authors = dao.readAll();

		assertThat(authors).hasSize(2).containsExactlyInAnyOrderElementsOf(
				Arrays.asList(new Author(1L, "Homer"), new Author(2L, "Shakespeare")));

	}

	@Test
	@DisplayName("should add Student")
	void shouldInsert() {
		dao.create(new Author(null, "Student"));

		List<Author> authors = dao.readAll();

		assertThat(authors).hasSize(3).contains(new Author(3L/* correct id, could be another policy */, "Student"));

	}
}
