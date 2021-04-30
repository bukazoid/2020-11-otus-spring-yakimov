package ru.yakimov.spring.mongo.service;

import java.util.List;

import ru.yakimov.spring.mongo.domain.Author;

public interface AuthorService {

	long count();

	Author read(String id);

	Author create(Author author);

	List<Author> readAll();

	Author update(Author author);
}
