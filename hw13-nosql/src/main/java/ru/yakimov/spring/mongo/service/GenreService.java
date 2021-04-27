package ru.yakimov.spring.mongo.service;

import java.util.List;

import ru.yakimov.spring.mongo.domain.Genre;

public interface GenreService {

	long count();

	Genre read(String id);

	Genre create(Genre genre);

	List<Genre> readAll();

	Genre update(Genre genre);

}
