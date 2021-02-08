package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.yakimov.spring.db.domain.Genre;
import ru.yakimov.spring.db.repositories.GenreRepository;

@DataJpaTest
@DisplayName("GenreDaoTest")
public class GenreRepositoryTest {
	@Autowired
	private GenreRepository repo;

	@DisplayName("should return 5")
	@Test
	public void shouldReturnCount() {
		assertEquals(5, repo.count());
	}

	@DisplayName("should read Homer")
	@Test
	public void shouldReturnHomer() {
		String name = repo.findById(1L).map(Genre::getName).orElse(null);
		assertEquals("Drama", name);
	}

	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		List<Genre> authors = repo.findAll();

		assertThat(authors).hasSize(5).containsExactlyInAnyOrderElementsOf(
				Arrays.asList(new Genre(1L, "Drama"), new Genre(2L, "Science Fiction"), new Genre(3L, "Fantasy"),
						new Genre(4L, "Detective"), new Genre(5L, "Historical")));
	}

	final static private String GENRE_TO_ADD = "Student Project";

	@Test
	@DisplayName("should add Student")
	void shouldInsert() {
		long count = repo.count();
		long exprectedCount = count + 1;

		repo.save(new Genre(null, GENRE_TO_ADD));

		List<Genre> authors = repo.findAll();

		assertThat(authors).hasSize((int) exprectedCount).contains(new Genre(exprectedCount, GENRE_TO_ADD));

	}
}
