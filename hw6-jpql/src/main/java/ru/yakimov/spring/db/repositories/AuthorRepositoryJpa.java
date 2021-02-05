package ru.yakimov.spring.db.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ru.yakimov.spring.db.domain.Author;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(a) FROM Author a", Long.class).getSingleResult();
	}

	@Override
	public Author read(Long id) {
		return em.find(Author.class, id);
	}

	@Override
	public Author create(Author author) {
		em.persist(author);
		return author;
	}

	@Override
	public List<Author> readAll() {
		TypedQuery<Author> query = em.createQuery("SELECT b FROM Author b", Author.class);
		return query.getResultList();
	}

}