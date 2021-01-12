package ru.yakimov.spring.db.dao;

import java.util.List;

import ru.yakimov.spring.db.domain.Author;

public interface AuthorDao {

	long count();

	Author read(Long id);

	Author create(Author author);

	List<Author> readAll();

}
