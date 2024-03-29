package ru.yakimov.spring.mvc.service;

import java.util.List;

import ru.yakimov.spring.mvc.domain.Genre;

public interface GenreService {

	long count();

	Genre read(Long id);

	Genre create(Genre genre);

	List<Genre> readAll();

	Genre update(Genre genre);

}
