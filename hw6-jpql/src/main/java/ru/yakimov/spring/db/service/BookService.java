package ru.yakimov.spring.db.service;

import java.util.List;

import ru.yakimov.spring.db.domain.Book;

public interface BookService {
	long count();

	Book read(Long id);
	
	void delete(Long id);

	Book create(Book book);

	List<Book> readAll();
}
