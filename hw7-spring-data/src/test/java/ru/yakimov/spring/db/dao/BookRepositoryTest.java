package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.Genre;
import ru.yakimov.spring.db.repositories.BookRepository;

@DataJpaTest
@DisplayName("BookRepositoryTest")
public class BookRepositoryTest {
	@Autowired
	private BookRepository repo;

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
		String title = repo.findById(1L).map(Book::getTitle).orElse(null);
		assertEquals("Illiad", title);
	}

	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {

		List<Book> books = repo.findAll();
		assertThat(books).hasSize(2);
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
		repo.save(new Book(BOOK_TO_ADD, author, genres));

		List<Book> books = repo.findAll();

		assertThat(books).hasSize((int) exprectedCount);

		Book book = em.find(Book.class, exprectedCount);

		assertThat(book).isNotNull();
		assertThat(book.getGenres()).contains(genre);

	}

	@Test
	@DisplayName("should delete")
	void shouldDeleteBooks() {
		repo.deleteById(1L);
		repo.deleteById(2L);

		assertThat(repo.findAll().size()).isEqualTo(0);
	}
}
