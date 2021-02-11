package ru.yakimov.spring.db.service;

import java.util.List;

import ru.yakimov.spring.db.domain.Genre;

public interface GenreService {

	long count();

	Genre read(Long id);

	Genre create(Genre author);

	List<Genre> readAll();

}
