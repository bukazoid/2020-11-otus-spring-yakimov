package ru.yakimov.spring.db.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.yakimov.spring.db.domain.BookComment;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(a) FROM BookComment a", Long.class).getSingleResult();
	}

	@Override
	public BookComment read(Long id) {
		return em.find(BookComment.class, id);
	}

	@Override
	public BookComment create(BookComment comment) {
		em.persist(comment);
		return comment;
	}

	@Override
	public void delete(Long id) {
		Query q = em.createQuery("DELETE FROM BookComment c WHERE c.id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	@Override
	public List<BookComment> readAll() {
		return em.createQuery("SELECT c FROM BookComment c JOIN FETCH c.book", BookComment.class).getResultList();
	}

	@Override
	public void update(BookComment comment) {
		em.merge(comment);
	}

}