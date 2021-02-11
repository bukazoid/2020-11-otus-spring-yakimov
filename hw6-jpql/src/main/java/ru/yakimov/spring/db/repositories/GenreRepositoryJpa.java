package ru.yakimov.spring.db.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ru.yakimov.spring.db.domain.Genre;

@Repository
public class GenreRepositoryJpa implements GenreRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(g) FROM Genre g", Long.class).getSingleResult();
	}

	@Override
	public Genre read(Long id) {
		return em.find(Genre.class, id);
	}

	@Override
	public Genre create(Genre genre) {
		em.persist(genre);
		return genre;
	}

	@Override
	public List<Genre> readAll() {
		TypedQuery<Genre> query = em.createQuery("SELECT b FROM Genre b", Genre.class);
		return query.getResultList();
	}

}