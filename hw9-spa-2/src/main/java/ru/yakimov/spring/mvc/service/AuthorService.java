package ru.yakimov.spring.mvc.service;

import java.util.List;

import ru.yakimov.spring.mvc.domain.Author;

public interface AuthorService {

	long count();

	Author read(Long id);
	
	Author create(Author author);

	List<Author> readAll();

}
