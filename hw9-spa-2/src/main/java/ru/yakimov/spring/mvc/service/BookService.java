package ru.yakimov.spring.mvc.service;

import java.util.List;

import ru.yakimov.spring.mvc.domain.Book;

public interface BookService {
	long count();

	Book read(Long id);
	
	void delete(Long id);

	Book create(Book book);

	List<Book> readAll();

	Book update(Book book);
}
