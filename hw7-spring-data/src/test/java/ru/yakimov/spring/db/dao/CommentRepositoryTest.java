package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.BookComment;
import ru.yakimov.spring.db.repositories.CommentRepository;

@Slf4j
@DataJpaTest
@DisplayName("CommentRepositoryTest")
public class CommentRepositoryTest {
	@Autowired
	private CommentRepository repo;

	@Autowired
	private TestEntityManager em;

	@DisplayName("should return count")
	@Test
	public void shouldReturnCount() {
		assertEquals(comments.size(), repo.count());
	}

	List<BookComment> comments = Arrays.asList(new BookComment(1L, new Book(1L), "Boooring"), // 1
			new BookComment(2L, new Book(1L), "Acient"), // 2
			new BookComment(3L, new Book(1L), "nice!"), // 3
			new BookComment(4L, new Book(1L), "too long"), // 4 for first book
			new BookComment(5L, new Book(2L), "Cool"), new BookComment(6L, new Book(2L), "I prefer movie"),
			new BookComment(7L, new Book(2L), "Too many words"));

	private final static int EXPECTED_QUERIES_COUNT = 1;//
	private final static int FIRST_BOOK_COMMENT_COUNT = 4;

	/**
	 * agree that this is discussable test
	 */
	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		// prepare comments

		// enable statistics
		SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
		sessionFactory.getStatistics().setStatisticsEnabled(true);

		// do the test
		List<BookComment> comments = repo.findAll();
		assertThat(comments).hasSize(comments.size());
		assertThat(comments).containsAnyElementsOf(comments);
		assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

		sessionFactory.getStatistics().setStatisticsEnabled(false);
	}

	@DisplayName("should read By book")
	@Test
	public void shouldReadByBook() {
		// just first book's comments
		List<BookComment> allComments = comments.stream().filter(c -> c.getBook().getId() == 1L)
				.collect(Collectors.toList());

		// do the test
		List<BookComment> comments = repo.findByBookId(1L);
		// asserts
		assertThat(comments).hasSize(FIRST_BOOK_COMMENT_COUNT);
		assertThat(comments).containsAnyElementsOf(allComments);
	}

	private final static String COMMENT = "Booooring";

	@Test
	@DisplayName("should add Comment")
	void shouldInsert() {
		Book someBook = em.find(Book.class, 1L);
		long count = repo.count();
		repo.save(new BookComment(null, someBook, COMMENT));

		List<BookComment> comments = repo.findAll();

		assertThat(comments).hasSize((int) count + 1).contains(new BookComment(count + 1, someBook, COMMENT));
	}

	private final static String NEW_COMMENT = "Could have been better";

	@Test
	@DisplayName("should update Comment")
	void shouldUpdate() {
		Book someBook = em.find(Book.class, 1L);
		BookComment comment = repo.save(new BookComment(null, someBook, COMMENT));
		log.info("id: {}", comment.getId());

		comment.setText(NEW_COMMENT);
		repo.save(comment);

		comment = repo.getOne(comment.getId());

		assertThat(comment.getText()).isEqualTo(NEW_COMMENT);

	}

	@Test
	@DisplayName("should delete")
	void shouldDeleteBooks() {
		List<BookComment> commentsWas = repo.findByBookId(1L);
		int sizeWas = commentsWas.size();

		repo.deleteById(commentsWas.get(0).getId());

		List<BookComment> commentsNew = repo.findByBookId(1L);
		int sizeNew = commentsNew.size();

		assertThat(sizeNew).isEqualTo(sizeWas - 1);
	}
}
