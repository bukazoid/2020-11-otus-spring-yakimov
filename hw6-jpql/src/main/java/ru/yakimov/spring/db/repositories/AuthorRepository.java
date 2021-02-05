package ru.yakimov.spring.db.repositories;

import java.util.List;

import ru.yakimov.spring.db.domain.Author;

public interface AuthorRepository {

	long count();

	Author read(Long id);

	Author create(Author author);

	List<Author> readAll();

}
