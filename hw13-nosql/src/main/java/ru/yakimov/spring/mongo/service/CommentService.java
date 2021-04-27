package ru.yakimov.spring.mongo.service;

import java.util.List;

import ru.yakimov.spring.mongo.domain.BookComment;

public interface CommentService {
	long count();

	BookComment read(String id);

	void delete(String id);

	void update(BookComment comment);

	BookComment create(BookComment book);

	List<BookComment> readAll();

	List<BookComment> readByBook(String bookId);
}