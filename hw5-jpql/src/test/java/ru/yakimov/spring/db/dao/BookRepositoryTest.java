package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.Genre;
import ru.yakimov.spring.db.repositories.AuthorRepositoryJpa;
import ru.yakimov.spring.db.repositories.BookRepositoryJpa;
import ru.yakimov.spring.db.repositories.GenreRepositoryJpa;

@DataJpaTest
@Import({ BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class })
@DisplayName("BookRepositoryTest")
public class BookRepositoryTest {
	@Autowired
	private BookRepositoryJpa repo;

	@Autowired
	private TestEntityManager em;

	@DisplayName("should return 2")
	@Test
	public void shouldReturnCount() {
		assertEquals(2, repo.count());
	}

	@DisplayName("should return Illiad")
	@Test
	public void shouldReturnHomer() {
		String title = Optional.ofNullable(repo.read(1L)).map(Book::getTitle).orElse(null);
		assertEquals("Illiad", title);
	}

	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		List<Book> authors = repo.readAll();

		assertThat(authors).hasSize(2);
	}

	final static private String BOOK_TO_ADD = "Student Diary";
	final static private String AUTHOR_ADD = "Student";

	@Test
	@DisplayName("should add diary")
	void shouldInsert() {
		long count = repo.count();
		long exprectedCount = count + 1;

		Author author = em.persist(new Author(null, AUTHOR_ADD));
		Genre genre = em.find(Genre.class, 1L);

		List<Genre> genres = Arrays.asList(genre);
		repo.create(new Book(BOOK_TO_ADD, author, genres));

		List<Book> books = repo.readAll();

		assertThat(books).hasSize((int) exprectedCount);

		Book book = em.find(Book.class, exprectedCount);

		assertThat(book).isNotNull();
		assertThat(book.getGenres()).contains(genre);

	}
}
