package ru.yakimov.spring.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.jline.utils.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
	private EntityManager em;

	@DisplayName("should return 0")
	@Test
	public void shouldReturnCount() {
		assertEquals(0, repo.count());
	}

	@DisplayName("should read all")
	@Test
	public void shouldReadAll() {
		List<BookComment> authors = repo.readAll();
		assertThat(authors).hasSize(0);
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
}
