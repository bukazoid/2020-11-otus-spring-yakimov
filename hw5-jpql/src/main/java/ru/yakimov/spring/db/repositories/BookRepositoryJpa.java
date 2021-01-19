package ru.yakimov.spring.db.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jline.utils.Log;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Book;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
	}

	@Override
	public Book read(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public void delete(Long id) {
		Book book = em.find(Book.class, id);
		if (book == null) {
			Log.info("book with id: {} is not found", id);
			return;
		}
		em.remove(book);
	}

	@Override
	public Book create(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public List<Book> readAll() {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

}
