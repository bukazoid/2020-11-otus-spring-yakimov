package ru.yakimov.spring.db.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jline.utils.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yakimov.spring.db.domain.BookComment;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(a) FROM BookComment a", Long.class).getSingleResult();
	}

	@Transactional(readOnly = true)
	@Override
	public BookComment read(Long id) {
		return em.find(BookComment.class, id);
	}

	@Transactional
	@Override
	public BookComment create(BookComment comment) {
		em.persist(comment);
		return comment;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		BookComment cmt = em.find(BookComment.class, id);
		if (cmt == null) {
			Log.info("book with id: {} is not found", id);
			return;
		}
		em.remove(cmt);
	}

	@Transactional(readOnly = true)
	@Override
	public List<BookComment> readAll() {
		return em.createQuery("SELECT b FROM BookComment b", BookComment.class).getResultList();
	}

	@Transactional
	@Override
	public void update(BookComment comment) {
		em.merge(comment);
	}

}