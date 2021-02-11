package ru.yakimov.spring.db.repositories;

import java.util.List;

import ru.yakimov.spring.db.domain.Book;

public interface BookRepository {

	long count();

	Book read(Long id);

	Book create(Book book);

	List<Book> readAll();

	void delete(Long id);
}
