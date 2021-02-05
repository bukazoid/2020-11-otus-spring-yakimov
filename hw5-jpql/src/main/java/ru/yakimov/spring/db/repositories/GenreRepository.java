package ru.yakimov.spring.db.repositories;

import java.util.List;

import ru.yakimov.spring.db.domain.Genre;

public interface GenreRepository {

	long count();

	Genre read(Long id);

	Genre create(Genre author);

	List<Genre> readAll();

}
