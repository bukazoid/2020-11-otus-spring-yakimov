package ru.yakimov.spring.db.service;

import java.util.List;

import ru.yakimov.spring.db.domain.Author;

public interface AuthorService {

	long count();

	Author read(Long id);
	
	Author create(Author author);

	List<Author> readAll();

}
