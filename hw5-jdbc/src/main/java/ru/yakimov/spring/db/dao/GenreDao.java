package ru.yakimov.spring.db.dao;

import java.util.List;

import ru.yakimov.spring.db.domain.Genre;

public interface GenreDao {

	long count();

	Genre read(Long id);

	Genre create(Genre author);

	List<Genre> readAll();

}
