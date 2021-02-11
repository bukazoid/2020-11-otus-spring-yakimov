package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.BookComment;
import ru.yakimov.spring.db.repositories.BookRepositoryJpa;
import ru.yakimov.spring.db.repositories.CommentRepository;
import ru.yakimov.spring.db.repositories.CommentRepositoryJpa;

@Slf4j
@DataJpaTest
@Import({ CommentRepositoryJpa.class, BookRepositoryJpa.class })
@DisplayName("CommentRepositoryTest")
public class CommentRepositoryTest {
	@Autowired
	private CommentRepository repo;

	@Autowired
	private TestEntityManager em;

	@DisplayName("should return 0")
	@Test
	public void shouldReturnCount() {
		assertEquals(0, repo.count());
	}

	private List<BookComment> fillComments(Book book, int n) {
		List<BookComment> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(em.persist(new BookComment(null, book, "comment " + i)));
		}

		return list;
	}

	private final static int EXPECTED_QUERIES_COUNT = 1;//

	/**
	 * agree that this is discussable test
	 */
	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		// prepare comments
		Book book1 = em.find(Book.class, 1L);
		Book book2 = em.find(Book.class, 2L);

		List<BookComment> allComments = new ArrayList<>();

		allComments.addAll(fillComments(book1, 4));
		allComments.addAll(fillComments(book2, 6));

		// enable statistics
		SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
		sessionFactory.getStatistics().setStatisticsEnabled(true);

		// do the test
		List<BookComment> comments = repo.readAll();
		assertThat(comments).hasSize(10);
		assertThat(comments).containsAnyElementsOf(allComments);
		assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

		log.info("genres: {}", comments.get(0).getBook().getGenres());// funny, how it load genres? first level cache?
	}

	private final static String COMMENT = "Booooring";

	@Test
	@DisplayName("should add Comment")
	void shouldInsert() {
		Book someBook = em.find(Book.class, 1L);
		long count = repo.count();
		repo.create(new BookComment(null, someBook, COMMENT));

		List<BookComment> comments = repo.readAll();

		assertThat(comments).hasSize((int) count + 1).contains(new BookComment(count + 1, someBook, COMMENT));
	}

	private final static String NEW_COMMENT = "Could have been better";

	@Test
	@DisplayName("should update Comment")
	void shouldUpdate() {
		Book someBook = em.find(Book.class, 1L);
		BookComment comment = repo.create(new BookComment(null, someBook, COMMENT));
		log.info("id: {}", comment.getId());

		comment.setText(NEW_COMMENT);
		repo.update(comment);

		comment = repo.read(comment.getId());

		assertThat(comment.getText()).isEqualTo(NEW_COMMENT);

	}

	@Test
	@DisplayName("should delete")
	void shouldDeleteBooks() {
		Book book = em.find(Book.class, 1L);
		BookComment comment = em.persist(new BookComment(null, book, NEW_COMMENT));

		log.info("comment id: {}", comment.getId());// id != 1 - oooook
		repo.delete(comment.getId());

		assertThat(repo.readAll().size()).isEqualTo(0);
	}
}
