package ru.yakimov.spring.mongo.service;

import java.util.List;

import ru.yakimov.spring.mongo.domain.Book;

public interface BookService {
	long count();

	Book read(String id);
	
	void delete(String id);

	Book create(Book book);

	List<Book> readAll();

	Book update(Book book);
}
